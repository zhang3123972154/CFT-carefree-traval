package com.xiaoyao.travel.config;

import com.xiaoyao.travel.common.dao.UserMapper;
import com.xiaoyao.travel.custom.CaptchaAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 配置基本的security拦截
 * @createTime : 2023/4/17 8:03
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/17 8:03
 * @updateRemark : 说明本次修改内容
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private UserMapper userMapper;
  @Resource
  private RedisTemplate redisTemplate;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint;
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
            .antMatchers("/user/register","/user/sendEmailCode","/user/loginByUsername").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/v2/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin().disable()
      .exceptionHandling()
      .authenticationEntryPoint(loginUrlAuthenticationEntryPoint);
    http.authenticationProvider(new CaptchaAuthenticationProvider(userMapper,redisTemplate));
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }


}
