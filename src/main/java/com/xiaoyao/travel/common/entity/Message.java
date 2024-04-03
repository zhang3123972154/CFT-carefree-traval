package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 好友之间的聊天记录
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer messageId;
	/**
	 * 
	 */
	private Integer senderId;
	/**
	 * 
	 */
	private Integer receiverId;
	/**
	 * 
	 */
	private Date sendTime;
	/**
	 * 1表示已读，0表示未读
	 */
	private Integer isReceived;
	/**
	 * 
	 */
	private Integer senderIsDeleted;
	/**
	 * 
	 */
	private Integer receiverIsDeleted;

}
