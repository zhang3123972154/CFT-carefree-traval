package com.xiaoyao.travel.custom;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @createTime : 2023/4/21 8:53
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/21 8:53
 * @updateRemark : 说明本次修改内容
 */

public class CaptchaAuthenticationToken extends AbstractAuthenticationToken {


  // ~ Instance fields
  // ========================================================================================================================================================================
  private final Object principal;
  private CaptchaSms credentials;

  // ~ Constructors
  // ========================================================================================================================================================================

  public CaptchaAuthenticationToken(Object principal,CaptchaSms credentials) {
    super(null);
    this.credentials = credentials;
    this.principal = principal;
    setAuthenticated(false);
  }

  // ~ Override Methods
  // ========================================================================================================================================================================
  @Override
  public Object getCredentials() {
    return this.credentials;
  }

  @Override
  public Object getPrincipal() {
    return this.principal;
  }

  @Data
  public static class CaptchaSms {
    private String captcha;
  }
}
