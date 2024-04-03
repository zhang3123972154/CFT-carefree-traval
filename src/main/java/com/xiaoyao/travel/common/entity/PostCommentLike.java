package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 帖子的评论的点赞表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("post_comment_like")
public class PostCommentLike implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer postCommentLikeId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer postCommentId;
	/**
	 * 
	 */
	private Date likedTime;

}
