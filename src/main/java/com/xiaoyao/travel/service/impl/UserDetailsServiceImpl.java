package com.xiaoyao.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoyao.travel.common.dao.UserMapper;
import com.xiaoyao.travel.common.entity.User;
import com.xiaoyao.travel.common.vo.response.UserLoginVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 16:44
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/11 16:44
 * @updateRemark : 说明本次修改内容
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  /**
   * 在规定username前面增加一个前缀指明username类型，空格隔开真正的数据，这是电话号码作为username的前缀
   */

  private static final String OAUTH_USERNAME_TEL_PREFIX = "tel";
  /**
   * 这是用户id作为username的前缀
   */
  private static final String OAUTH_USERNAME_ID_PREFIX = "username";
  @Resource
  private UserMapper userMapper;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 首先要判断username类型
    String[] usernamePart = username.split(" ");
    if (usernamePart.length < 2) {
      throw new UsernameNotFoundException("用户名为空");
    }
    UserLoginVo user = new UserLoginVo();
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    if (OAUTH_USERNAME_TEL_PREFIX.equals(usernamePart[0])) {
      userQueryWrapper.eq("phone",usernamePart[1]);
      User selectOne = userMapper.selectOne(userQueryWrapper);
      BeanUtil.copyProperties(selectOne,user);
    } else if (OAUTH_USERNAME_ID_PREFIX.equals(usernamePart[0])) {
      userQueryWrapper.eq("email",usernamePart[1]);
      User selectOne = userMapper.selectOne(userQueryWrapper);
      BeanUtil.copyProperties(selectOne,user);
    } else {
      throw new UsernameNotFoundException("用户名格式错误");
    }
    if (user == null || user.getUserName() == null) {
      throw new UsernameNotFoundException("用户名不存在");
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    return org.springframework.security.core.userdetails.User.withUsername(user.getUserId().toString())
      .password(user.getPassword())
      .disabled(user.getStatus().equals(1))
      .authorities(authorities)
      .build();
  }
}
