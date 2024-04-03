package com.xiaoyao.travel.common.vo.response;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/12 18:23
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/12 18:23
 * @updateRemark : 说明本次修改内容
 */
@Data
public class LoginResponseVo {
  private static final long serialVersionUID = 914967629530462926L;
  private String access_token;
  private long expires_in;
  private String token_type;
  private String refresh_token;
  private Set<String> scope;
  private Map<String, Object> additionalInformation;



}
