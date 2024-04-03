package com.xiaoyao.travel.common.constants;

import lombok.Getter;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/13 19:32
 */
@Getter
public enum CaptchaPurpose {
  REGISTER(0,"REGISTER"),
  LOGIN(1,"LOGIN")
  ;

  private final Integer key;
  private final String purpose;
  CaptchaPurpose(Integer key,String purpose){
    this.key = key;
    this.purpose = purpose;
  }
  public static String getByNum(Integer key){
    for (CaptchaPurpose value : CaptchaPurpose.values()) {
      if (value.key.equals(key)){
        return value.purpose;
      }
    }
    throw new RuntimeException("purposeIdentify字段不存在");

  }
}
