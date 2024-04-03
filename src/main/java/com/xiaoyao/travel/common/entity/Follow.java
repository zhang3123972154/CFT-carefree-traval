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
@TableName("follow")
public class Follow implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer followId;
	/**
	 * 发起关注的人的id
	 */
	private Integer followerId;
	/**
	 * 被关注的人的id
	 */
	private Integer followedId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
