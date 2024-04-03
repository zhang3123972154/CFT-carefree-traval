package com.xiaoyao.travel.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 20:25
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/11 20:25
 * @updateRemark : 说明本次修改内容
 */

public class VerifyUtils {
  public static boolean checkEmail(String email) {
    boolean flag = false;
    try {
      String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
      Pattern regex = Pattern.compile(check);
      Matcher matcher = regex.matcher(email);
      flag = matcher.matches();
    } catch (Exception e) {
      flag = false;
    }
    return flag;
  }
}
