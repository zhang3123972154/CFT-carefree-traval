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
@TableName("brows_history")
public class BrowsHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer historyId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 浏览的帖子id
	 */
	private Integer postId;
	/**
	 * 浏览的景点id
	 */
	private Integer attractionId;
	/**
	 * 浏览时间
	 */
	private Date createTime;

}
