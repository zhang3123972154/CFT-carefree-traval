package com.xiaoyao.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : token策略
 * @createTime : 2023/4/17 8:03
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/17 8:03
 * @updateRemark :
 */
@Configuration
public class TokenConfig {
  @Resource
  private DataSource dataSource;

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
  public TokenStore tokenStore(){
    return new JdbcTokenStore(dataSource);
  }

}
