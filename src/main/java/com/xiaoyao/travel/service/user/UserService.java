package com.xiaoyao.travel.service.user;

import com.xiaoyao.travel.common.vo.request.UserLoginRequestVo;
import com.xiaoyao.travel.common.vo.response.UserLoginVo;
import org.springframework.stereotype.Service;

/**
 * @author cinder
 */
@Service
public interface UserService {
  UserLoginVo login(UserLoginRequestVo userLoginRequestVo);

  void getVerificationCode(Integer countType, String count);
}
