package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 帖子点赞表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("post_like")
public class PostLike implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer postLikeId;
	/**
	 * 点赞用户id
	 */
	private Integer userId;
	/**
	 * 帖子id
	 */
	private Integer postId;
	/**
	 * 点赞的时间
	 */
	private Date createTime;

}
