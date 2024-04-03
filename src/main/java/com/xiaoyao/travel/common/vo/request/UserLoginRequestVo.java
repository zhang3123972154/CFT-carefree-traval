package com.xiaoyao.travel.common.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cinder
 */
@Data
@ApiModel("用户登陆需要传的参数")
public class UserLoginRequestVo {
  @ApiModelProperty("用户名，如果用邮箱密码登陆，该字段为邮箱。如果用手机号密码登陆，该字段为手机号。如果用验证码登陆，该字段为对应的账号（手机号或者邮箱）")
  private String username;
  @ApiModelProperty("用户密匙，如果用手机号密码或者邮箱密码登陆，该字段为加密后的密码。如果用手机号、邮箱验证码登陆，该字段为验证码明文")
  private String password;
  @ApiModelProperty("登陆方式，0:邮箱密码登陆，1:手机号密码登陆，2:邮箱验证码登陆，3:手机号验证码登陆")
  private Integer loginType;
}
