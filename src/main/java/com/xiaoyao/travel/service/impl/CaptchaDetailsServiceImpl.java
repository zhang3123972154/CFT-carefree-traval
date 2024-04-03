package com.xiaoyao.travel.service.impl;

import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoyao.travel.common.dao.UserMapper;
import com.xiaoyao.travel.common.entity.User;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.custom.CaptchaAuthenticationToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.xiaoyao.travel.common.constants.CaptchaPurpose.LOGIN;
import static com.xiaoyao.travel.common.constants.ResultCode.CAPTCHA_NOT_EXIST;
import static com.xiaoyao.travel.common.constants.ResultCode.ERROR_CAPTCHA;


/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 验证码登录校验services
 * @createTime : 2023/4/21 12:32
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/21 12:32
 * @updateRemark : 说明本次修改内容
 */

@Service
public class CaptchaDetailsServiceImpl {


  // ~ Methods
  //通过验证码登录时加载UserDetails
  // ========================================================================================================================================================================
  public UserDetails loadUserByCaptcha(String phone, CaptchaAuthenticationToken.CaptchaSms captchaSms,
                                       RedisTemplate redisTemplate,
                                       UserMapper userMapper
  ){
    //获取验证码
    String key = LOGIN.getPurpose() + " " + phone;
    Object o = redisTemplate.opsForValue().get(key);
    if (o == null){
      throw new ServiceException(CAPTCHA_NOT_EXIST);
    }
    String captcha = o.toString();
    if (!captcha.equals(captchaSms.getCaptcha())){
      throw new ServiceException(ERROR_CAPTCHA);
    }
    QueryWrapper<User> userqueryWrapper = new QueryWrapper<>();
    if (Validator.isEmail(phone)) {
      userqueryWrapper.eq("email",phone);
    }else {
      userqueryWrapper.eq("phone",phone);
    }
    User user = userMapper.selectOne(userqueryWrapper);
    if (user == null ) {
      throw new ServiceException("");
    }

    //TODO 设置用户权限
    List<GrantedAuthority> authorities = new ArrayList<>();

    return org.springframework.security.core.userdetails.User.withUsername(user.getUserId().toString())
      .password(user.getPassword())
      .disabled(user.getStatus().equals(true))
      .authorities("all")
      .build();
  }


}
