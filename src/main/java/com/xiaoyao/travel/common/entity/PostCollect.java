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
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("post_collect")
public class PostCollect implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer postCollectId;
	/**
	 * 帖子id
	 */
	private Integer postId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 收藏时间
	 */
	private Date createTime;

}
