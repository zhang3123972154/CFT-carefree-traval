package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Data
@TableName("friends")
public class Friends implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer friendId;
	/**
	 * 用户1id
	 */
	private Integer user1Id;
	/**
	 * 用户2id
	 */
	private Integer user2Id;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
