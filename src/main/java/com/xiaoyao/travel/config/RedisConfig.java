package com.xiaoyao.travel.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : redisk key value序列化
 * @createTime : 2023/4/17 8:03
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/17 8:03
 * @updateRemark : 说明本次修改内容
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);

    GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

    //设置key的序列化
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setHashKeySerializer(RedisSerializer.string());

    //设置value的序列化
    redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);
    redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);

    return redisTemplate;
  }
}
