package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 帖子评论列表，存储评论的主要信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("post_comment")
public class PostComment implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer postCommentId;
	/**
	 * 
	 */
	private Integer postId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private String comment;
	/**
	 * 
	 */
	private Integer likedNum;
	/**
	 * 
	 */
	private Integer isDeleted;

}
