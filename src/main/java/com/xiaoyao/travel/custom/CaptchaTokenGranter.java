package com.xiaoyao.travel.custom;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/4/21 8:53
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/21 8:53
 * @updateRemark : 说明本次修改内容
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {

  /**
   * 授权模式名字
   */
  private static final String GRANT_TYPE = "captcha";

  private final AuthenticationManager authenticationManager;


  // ~ Constructors
  // ========================================================================================================================================================================
  public CaptchaTokenGranter(AuthenticationManager authenticationManager,
                             AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
    this(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE,authenticationManager);
  }

  protected CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices,
                                ClientDetailsService clientDetailsService,
                                OAuth2RequestFactory requestFactory,
                                String grantType,
                                AuthenticationManager authenticationManager) {
    super(tokenServices, clientDetailsService, requestFactory, grantType);
    this.authenticationManager = authenticationManager;
  }


  // ~ Override Methods
  // ========================================================================================================================================================================
  @Override
  protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
    Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
    String phone = parameters.get("username");
    String captcha = parameters.get("captcha");

    if (phone == null) {
      throw new InvalidRequestException("An telephone code must be supplied.");
    }
    if (captcha == null) {
      throw new InvalidRequestException("An captcha must be supplied.");
    }
    CaptchaAuthenticationToken.CaptchaSms captchaSms = new CaptchaAuthenticationToken.CaptchaSms();
    captchaSms.setCaptcha(captcha);

    Authentication userAuth = new CaptchaAuthenticationToken(phone, captchaSms);
    ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
    try {
      userAuth = authenticationManager.authenticate(userAuth);
    }
    catch (AccountStatusException ase) {
      //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
      throw new InvalidGrantException(ase.getMessage());
    }
    catch (BadCredentialsException e) {
      // If the username/password are wrong the spec says we should send 400/invalid grant
      throw new InvalidGrantException(e.getMessage());
    }
    if (userAuth == null || !userAuth.isAuthenticated()) {
      throw new InvalidGrantException("Could not authenticate user: " + phone);
    }
    OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
    return new OAuth2Authentication(storedOAuth2Request, userAuth);
  }
}
