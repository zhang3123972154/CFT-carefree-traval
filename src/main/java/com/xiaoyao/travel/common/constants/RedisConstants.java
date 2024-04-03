package com.xiaoyao.travel.common.constants;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 20:38
 */
public class RedisConstants {
  public static long LOGIN_CODE_TTL = 10L;

  // ~ redis verification
  // ========================================================================================================================================================================
  public enum cacheConstants {
    USER_BASIC_INFO,
    ALL_PROJECT,
    ID_PROJECT,
    PROJECT_LIST_BY_USERID,
    /**
     * @author Zhangxiaobo
     * @description 根据userid存储用户接下的order列表
     * @createTime  2023/7/16 17:54
     **/

    ACCEPTED_ORDER_LIST,
    /**
     * @author Zhangxiaobo
     * @description 根据userid存储用户发布的order列表
     * @createTime  2023/7/16 17:53
     **/
    POSTED_ORDER_LIST,
    /**
     * @author Zhangxiaobo
     * @description 根据orderid存储order信息
     * @createTime  2023/7/16 17:53
     **/

    ORDER_INFO_ID,
    /**
     * @author Zhangxiaobo
     * @description 是否正在修改分期信息，等待另一方同意
     * @createTime  2023/7/19 11:16
     **/
    EDIT_PROGRESSING,
    /**
     * @author Zhangxiaobo
     * @description 分期详情，包括是否有提交的修改
     * @createTime  2023/7/19 11:16
     **/
    PROGRESS_INFO,
  }
}
