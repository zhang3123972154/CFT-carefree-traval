package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发布帖子表，存储发布的帖子主要内容
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("post")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer postId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 帖子标题
	 */
	private String title;
	/**
	 * 帖子内容
	 */
	private String content;
	/**
	 * 点赞数量
	 */
	private Integer likedNum;
	/**
	 * 发布帖子的图片存储的文件夹
	 */
	private String imageDir;
	/**
	 * ai总结出来的个性词条
	 */
	private String individualKey;
	/**
	 * 偏好人群
	 */
	private String prefer;
	/**
	 * 
	 */
	private Integer commentNum;
	/**
	 * 
	 */
	private Integer collectNum;
	/**
	 * 
	 */
	private String category;
	/**
	 * 是否被删除
	 */
	private Integer isDeleted;

}
