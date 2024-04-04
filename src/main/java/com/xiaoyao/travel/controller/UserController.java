package com.xiaoyao.travel.controller;

import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.dao.UserMapper;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.common.utils.RSAUtils;
import com.xiaoyao.travel.common.vo.response.LoginResponseVo;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import com.xiaoyao.travel.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

import static com.xiaoyao.travel.common.utils.RSAUtils.keyMap;


/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 19:55
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/11 19:55
 * @updateRemark : 说明本次修改内容
 */
@RestController
@Api("用户接口模块")
@RequestMapping("/user")
public class UserController {

  @Resource
  private IUserService userService;

  @Resource
  private UserMapper userMapper;

  @PostMapping("/register")
  @ApiOperation(value = "用户通过邮箱注册账号，verification表示邮箱验证码",httpMethod = "POST")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "email",value = "注册用户用的email"),
    @ApiImplicitParam(name = "password",value = "md5加密之后的密码"),
    @ApiImplicitParam(name = "verification",value = "注册时用的验证码")
  })
  public ResponseBean register(@RequestParam String email, @RequestParam String password, @RequestParam String verification){
    try {
      userService.register(email,password,verification);
    }catch (ServiceException e){
      return ResponseBean.fail(e.getResultCode(),e.getMessage());
    }
    return ResponseBean.success();
  }

  @GetMapping("/sendEmailCode")
  @ApiOperation(value = "向邮箱发送验证码",httpMethod = "GET")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "email",value = "邮箱"),
    @ApiImplicitParam(name = "purposeIdentify",value = "表示发送此次验证码将用于什么,REGISTER:0,LOGIN:1")
  })
  public ResponseBean sendEmailCode(@RequestParam String email,@RequestParam Integer purposeIdentify){
    try {
      userService.sendEmailCode(email,purposeIdentify);
    }catch (ServiceException e){
      return ResponseBean.fail(e.getResultCode(),e.getMessage());
    }
    return ResponseBean.success();
  }

  @GetMapping("/loginByUsername")
  @ApiOperation(value = "用户名和密码登录",httpMethod = "GET")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "username",value = "邮箱"),
    @ApiImplicitParam(name = "password",value = "md5加密过的密码")
  })
  public ResponseBean loginByUsername(@RequestParam String username,@RequestParam String password){
    LoginResponseVo loginResponseVo;
    try {
      loginResponseVo = userService.loginByPassword(username, password,"username");
    }catch (ServiceException e){
      return ResponseBean.fail(e.getResultCode(),e.getMessage());
    }
    return ResponseBean.success(loginResponseVo);
  }
//  @GetMapping("/loginByTel")
//  @Description("通过手机号和密码登录，其中密码使用rsa加密，格式为:<br/> +" +
//    "phone: 176xxxx0984 <br/> +" +
//    "password: xxxxxxxxx")
//  public ResponseBean loginByTel(@RequestParam String phone,@RequestParam String password){
//    LoginResponseVo loginResponseVo;
//    String dePass;
//    if (keyMap.get(1) == null){
//      return ResponseBean.fail("私匙为空");
//    }
//    try {
//      dePass=RSAUtils.decrypt(password,keyMap.get(1));
//    } catch (Exception e) {
//      return ResponseBean.fail(ResultCode.PASSWORD_DECODE_ERROR);
//    }finally {
//      keyMap.clear();
//    }
//    try {
//      loginResponseVo = userService.loginByPassword(phone, dePass,"tel");
//    }catch (ServiceException e){
//      return ResponseBean.fail(e.getResultCode(),e.getMessage());
//    }
//    return ResponseBean.success(loginResponseVo);
//  }
//  @GetMapping("/loginByCaptcha")
//  @Description("邮箱或者手机和验证码登录")
//  public ResponseBean loginByCaptcha(@RequestParam String emailOrPhone,@RequestParam String verification){
//    LoginResponseVo loginResponseVo;
//    try {
//      loginResponseVo = userService.loginByCaptcha(emailOrPhone,verification);
//    }catch (ServiceException e){
//      return ResponseBean.fail(e.getResultCode(),e.getMessage());
//    }
//    return ResponseBean.success(loginResponseVo);
//  }

  @GetMapping("/refreshToken")
  @ApiOperation(value = "刷新token",httpMethod = "GET")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "refresh_token",value = "刷新token"),
  })
  public ResponseBean refreshToken(@RequestParam String refresh_token){
    LoginResponseVo loginResponseVo;
    try {
      loginResponseVo = userService.refreshToken(refresh_token);
    }catch (ServiceException e){
      return ResponseBean.fail(e.getResultCode(),e.getMessage());
    }
    return ResponseBean.success(loginResponseVo);
  }
//  @GetMapping("/getPubKey")
//  @Description("生成私匙和公匙，并返回公匙，公匙使用一次立马清除，无论请求成功还是失败")
//  public ResponseBean getPublicKey(){
//    try {
//      RSAUtils.getKeyPair();
//    } catch (Exception e) {
//      return ResponseBean.fail(e.getMessage());
//    }
//    return ResponseBean.success(keyMap.get(0));
//  }
}
