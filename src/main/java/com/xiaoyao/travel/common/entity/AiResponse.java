package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 14:49
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 14:49
 * @updateRemark : 说明本次修改内容
 */
@Data
@TableName("ai_response")
public class AiResponse implements Serializable {
  private static final long serialVersionUID = 1819775643078L;

  @TableId
  private Integer aiResponseId;
  /**
   * 用户id
   */
  private Integer dialogueId;
  /**
   * 一次的消息
   */
  private Integer aiId;
  /**
   * 消息发送时间
   */
  private Date responseTime;
  private String content;
}
