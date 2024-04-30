package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户个性表，存储用户的个性标签
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Data
@TableName("individuality")
public class Individuality implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 个人喜好id
	 */
	@TableId
	private Integer individualityId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 自己选择的关键词
	 */
	private String selfSelectedKey;
	/**
	 * 个人预算
	 */
	private Integer budget;
	/**
	 * 根据喜欢的帖子总结出来的关键词
	 */
	private String keyAboutPost;
	/**
	 * 根据ai对话总结出来的关键词
	 */
	private String keyAboutAi;
	/**
	 * 推荐原则
	 */
	private String recommendationPrinciple;

}
