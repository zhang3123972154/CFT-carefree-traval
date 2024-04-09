package com.xiaoyao.travel.common.vo.response;

import io.swagger.annotations.ApiModel;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/7 21:56
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/7 21:56
 * @updateRemark : 说明本次修改内容
 */
@ApiModel("ai返回图文推荐")
public class StrImageResponseVo {
  private String type;
  private String text;
  private String grade;
  private String location;
  private String price;
  private String imgPath;
}
