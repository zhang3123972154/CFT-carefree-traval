package com.xiaoyao.travel.common.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 15:00
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 15:00
 * @updateRemark : 说明本次修改内容
 */
@ApiModel("获取和ai对话的历史记录")
@Data
public class DialogueHistoryVo {
  @ApiModelProperty("消息内容")
  private String userSend;
  @ApiModelProperty("消息内容")
  private String aiResponse;
  @ApiModelProperty("发送消息的时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalTime sendTime;
  @ApiModelProperty("发送消息的时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalTime responseTime;
}
