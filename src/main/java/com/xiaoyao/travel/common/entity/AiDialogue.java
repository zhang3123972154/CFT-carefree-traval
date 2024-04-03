package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 和ai对话记录列表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Data
@TableName("ai_dialogue")
public class AiDialogue implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 和ai对话记录id
	 */
	@TableId
	private Integer dialogueId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 一次的消息
	 */
	private String message;
	/**
	 * 消息发送时间
	 */
	private Date sendTime;

}
