package com.xiaoyao.travel.common.vo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 14:35
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 14:35
 * @updateRemark : 说明本次修改内容
 */
@ApiModel("用户向ai发送消息")
@Data
public class SendToAiMsgVo {
  @ApiModelProperty("发消息的用户id")
  private Long userId;
  @ApiModelProperty("目标aiid")
  private Long aiId;
  @ApiModelProperty("消息内容")
  private String message;
  @ApiModelProperty("发送消息的时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date sendTime;
}