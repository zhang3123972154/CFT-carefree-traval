package com.xiaoyao.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoyao.travel.common.constants.CaptchaPurpose;
import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.dao.IndividualityMapper;
import com.xiaoyao.travel.common.dao.UserMapper;
import com.xiaoyao.travel.common.entity.Individuality;
import com.xiaoyao.travel.common.entity.User;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.common.vo.response.IndividualVo;
import com.xiaoyao.travel.common.vo.response.IntegerAndStringVo;
import com.xiaoyao.travel.common.vo.response.LoginResponseVo;
import com.xiaoyao.travel.common.vo.response.PageDTO;
import com.xiaoyao.travel.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.xiaoyao.travel.common.constants.CaptchaPurpose.REGISTER;
import static com.xiaoyao.travel.common.constants.RedisConstants.LOGIN_CODE_TTL;
import static com.xiaoyao.travel.common.constants.ResultCode.ERROR_SIGNIN;
import static com.xiaoyao.travel.common.utils.VerifyUtils.checkEmail;


/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 20:04
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/11 20:04
 * @updateRemark : 说明本次修改内容
 */
@Service
public class UserServiceImpl implements IUserService {
  @Value("${spring.mail.username}")
  private String from;
  @Resource
  private UserMapper userMapper;
  @Resource
  private JavaMailSender mailSender;
  @Resource
  private RedisTemplate redisTemplate;

  @Value("${security.oauth2.client.access-token-url}")
  private String accessTokenUrl;

  @Autowired
  private IndividualityMapper individualityMapper;


  @Override
  public void register(String email, String password,String verification) {
    if (email.isEmpty() || password.isEmpty()){
      throw new ServiceException(ResultCode.MISS_PARAMETER);
    }
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("email",email);
    User selectOne = userMapper.selectOne(userQueryWrapper);
    if (selectOne != null) {
      throw new ServiceException(ResultCode.EXIST_USER);
    }
    String verificationCode = redisTemplate.opsForValue().get(REGISTER + " " + email).toString();
    if (!verification.equals(verificationCode)){
      throw new ServiceException(ResultCode.INVALID_CAPTCHA);
    }
    int insert;
    //TODO 使用雪花算法生成全局唯一username
    User user = new User();
    user.setEmail(email);
    user.setPassword(new BCryptPasswordEncoder().encode(password));
    user.setUserName("xiaoyao_"+RandomUtil.randomString(7));
    insert = userMapper.insert(user);
    if (insert != 1){
      throw new ServiceException(ResultCode.DATABASE_ERROR);
    }
  }

  @Override
  public void sendEmailCode(String email, Integer purposeIdentify) {
    if (!checkEmail(email)){
      throw new RuntimeException("请正确输入邮箱");
    }
    String verification = RandomUtil.randomNumbers(6);
    //存入redis
    try {
      String codeKey= CaptchaPurpose.getByNum(purposeIdentify) + " "+ email;
      redisTemplate.opsForValue().set(codeKey,verification,LOGIN_CODE_TTL, TimeUnit.MINUTES);
    }catch (RuntimeException e){
      throw new RuntimeException("验证码存储错误");
    }
    try {
      send(email,verification);
    }catch (Exception e){
      throw new RuntimeException("验证码发送失败");
    }
  }

  @Override
  public LoginResponseVo loginByPassword(String username, String password, String purpose) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Object> exchange;
    username = purpose + " "+username;
    String url = accessTokenUrl+ "?client_secret=password&client_id=app&grant_type=password&username="+username+"&password="+password;
    try {
      exchange = restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, Object.class);
    }catch (Exception e){
      throw new ServiceException(e.getMessage(),ERROR_SIGNIN);
    }
    LoginResponseVo loginResponseVo = new LoginResponseVo();
    BeanUtil.copyProperties(exchange.getBody(),loginResponseVo);
    return loginResponseVo;
  }

  @Override
  public LoginResponseVo refreshToken(String refreshToken) {
    ResponseEntity<Object> responseEntity;
    RestTemplate restTemplate = new RestTemplate();
    String url = accessTokenUrl+ "?client_secret=password&client_id=app&grant_type=refresh_token&refresh_token="+refreshToken;
    try {
      responseEntity = restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, Object.class);
    }catch (Exception e){
      throw new ServiceException(e.getMessage(),ERROR_SIGNIN);
    }
    if (responseEntity.getStatusCode() != HttpStatus.OK){
      throw new ServiceException("请求refresh_token失败",ResultCode.ERROR);
    }
    LoginResponseVo loginResponseVo = new LoginResponseVo();
    BeanUtil.copyProperties(responseEntity.getBody(),loginResponseVo);
    return loginResponseVo;
  }

  @Override
  public void uploadIndividual(List<String> individual) {
    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = user.getUsername();
    if (userId.isEmpty()) {
      throw new ServiceException(ResultCode.ERROR_TOKEN_FORMAT);
    }

    for (String s : individual) {
      List<Individuality> individualities = individualityMapper.selectList(new LambdaQueryWrapper<Individuality>().eq(Individuality::getKeyAboutPost, s));
      if (!individualities.isEmpty()) {
        continue;
      }
      Individuality individuality = new Individuality();
      individuality.setUserId(Long.parseLong(userId));
      individuality.setKeyAboutPost(s);
      individuality.setBudget(0);
      individualityMapper.insert(individuality);
    }
  }

  @Override
  public PageDTO<IntegerAndStringVo> searchIndividual(String keyword, Integer pageSize, Integer pageNum) {
    Page<Individuality> page = new Page<>(pageNum,pageSize);
    IPage<Individuality> individualities = individualityMapper.selectPage(page, new LambdaQueryWrapper<Individuality>()
            .like(!keyword.isEmpty(), Individuality::getKeyAboutAi, keyword)
            .or().like(!keyword.isEmpty(), Individuality::getKeyAboutPost, keyword));
    List<IntegerAndStringVo> data = individualities.getRecords().stream().map(e -> new IntegerAndStringVo(e.getIndividualityId(), e.getKeyAboutPost().isEmpty() ?
            e.getKeyAboutAi() : e.getKeyAboutPost())).collect(Collectors.toList());
    return new PageDTO<IntegerAndStringVo>(pageNum,pageSize,data.size(),data);

  }

  @Override
  public List<IntegerAndStringVo> randomIndividual() {
    return userMapper.selectRandomIndividual(50);
  }

  private void send(String to, String code) {
    SimpleMailMessage message = new SimpleMailMessage();
    // 设置邮件主题
    message.setSubject("登录验证码");
    // 邮件的内容
    message.setText(contentBild(code, 5));
    // 设置接收者邮箱
    message.setTo(to);
    // 设置发送者邮箱
    message.setFrom(from);
    // 发送
    mailSender.send(message);
  }
  private String contentBild(String code, int expireIn) {
    StringBuilder sb = new StringBuilder();
    sb.append("您的验证码:");
    sb.append(code);
    sb.append(",有效时效:");
    sb.append(expireIn);
    sb.append("分钟");
    return sb.toString();
  }
}
