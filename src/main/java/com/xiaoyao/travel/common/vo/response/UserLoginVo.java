package com.xiaoyao.travel.common.vo.response;

import lombok.Data;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 17:03
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/11 17:03
 * @updateRemark : 说明本次修改内容
 */
@Data
public class UserLoginVo {
  private Long userId;
  private String userName;
  private String password;
  private String email;
  private String phoneNumber;
  /**
   * 状态（0正常 1禁用）
   */
  private String status;

}
