package com.xiaoyao.travel.service.user.Impl;

import com.xiaoyao.travel.common.vo.request.UserLoginRequestVo;
import com.xiaoyao.travel.common.vo.response.UserLoginVo;
import com.xiaoyao.travel.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * @author cinder
 */
public class UserServiceImpl implements UserService {
  @Override
  public UserLoginVo login(UserLoginRequestVo userLoginRequestVo) {
    return null;
  }

  @Override
  public void getVerificationCode(Integer countType, String count) {

  }
}
