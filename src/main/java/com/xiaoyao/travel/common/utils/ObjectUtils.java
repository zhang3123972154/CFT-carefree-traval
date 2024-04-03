package com.xiaoyao.travel.common.utils;

import java.lang.reflect.Field;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/19 11:03
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/19 11:03
 * @updateRemark : 说明本次修改内容
 */

public class ObjectUtils {
  public static boolean compareTwoObject(Object class1, Object class2) {
    // 获取对象class
    Class<?> clazz1 = class1.getClass();
    Class<?> clazz2 = class2.getClass();
    // 获取类中所有声明的字段
    Field[] fields1 = clazz1.getDeclaredFields();
    Field[] fields2 = clazz2.getDeclaredFields();
    // 遍历
    for (int i=0;i<fields1.length;i++) {
      for(int j=0;j<fields2.length;j++) {
                /*简单比较，如果两字段名称一致，就认为是同一个字段
                    ，默认考虑字段定义的类型一致*/
        if(fields1[i].getName().equals(fields2[j].getName())){
          // 将字段设置成可通过反射访问
          fields1[i].setAccessible(true);
          fields2[j].setAccessible(true);
          // 比较内容
          if(!compareTwo(fields1[i],fields2[j])) {
            // 有修改
            return false;
          }
          // 当前字段名称已经找到，直接终止最内层for循环，开始下一轮字段的匹配
          break;
        }
      }
    }
    return true;
  }

  private static boolean compareTwo(Object object1, Object object2) {
    // 如果两字段都为空，也认为相等
    if (object1 == null && object2 == null){
      return true;
    }
    // 视情况而定，正常页面表单没有填写数据，传到后台时是null
    // 但同一条信息，数据库里应该存的是""
    if (object1 == null && object2 == "") {
      return true;
    }
    // 比较内容
    if(object1.equals(object2)) {
      return true;
    }
    return false;
  }
}
