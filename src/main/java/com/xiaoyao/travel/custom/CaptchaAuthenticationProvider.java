package com.xiaoyao.travel.custom;


import com.xiaoyao.travel.common.dao.UserMapper;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.service.impl.CaptchaDetailsServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.Assert;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @createTime : 2023/4/21 8:53
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/21 8:53
 * @updateRemark : 说明本次修改内容
 */

public class CaptchaAuthenticationProvider implements AuthenticationProvider {

  // ~ Instance fields
  // ========================================================================================================================================================================
  private final UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();
  private CaptchaDetailsServiceImpl captchaDetailsService = new CaptchaDetailsServiceImpl();

  private UserMapper userMapper;

  private RedisTemplate redisTemplate;

  // ~ Constructors
  // ========================================================================================================================================================================

  public CaptchaAuthenticationProvider(UserMapper userMapper,RedisTemplate redisTemplate){
    this.userMapper = userMapper;
    this.redisTemplate = redisTemplate;
  }


  // ~ Override Methods
  // ========================================================================================================================================================================

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(CaptchaAuthenticationToken.class, authentication,
      "Only CaptchaAuthenticationToken is supported");
    UsernamePasswordAuthenticationToken auth = null;
    UserDetails userDetails = null;

    String phone = authentication.getName();
    CaptchaAuthenticationToken.CaptchaSms captchaSms = (CaptchaAuthenticationToken.CaptchaSms) authentication.getCredentials();

    try {
      userDetails = captchaDetailsService.loadUserByCaptcha(phone, captchaSms,redisTemplate,userMapper);
    }catch (ServiceException e){
      throw new ServiceException(e.getResultCode());
    }

    userDetailsChecker.check(userDetails);

    auth = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    auth.setDetails(authentication.getDetails());
    return auth;

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (CaptchaAuthenticationToken.class
      .isAssignableFrom(authentication));
  }
}
