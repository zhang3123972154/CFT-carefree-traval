package com.xiaoyao.travel.custom;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/4/21 8:53
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/21 8:53
 * @updateRemark : 说明本次修改内容
 */

public class CaptchaNotFoundException extends AuthenticationException {

  // ~ Medthods
  // ========================================================================================================================================================================
  public CaptchaNotFoundException(String msg, Throwable t) {
    super(msg, t);
  }

  public CaptchaNotFoundException(String msg) {
    super(msg);
  }
}
