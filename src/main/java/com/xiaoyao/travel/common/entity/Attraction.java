package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 景点列表，存放景点的位置等信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Data
@TableName("attraction")
public class Attraction implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 旅游景点id
	 */
	@TableId
	private Integer attractionId;
	/**
	 * 旅游景点名称
	 */
	private String attractionName;
	/**
	 * 该景点的经度
	 */
	private Double longitude;
	/**
	 * 该景点的维度
	 */
	private Double latitude;
	/**
	 * 分类
	 */
	private String category;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 电话号
	 */
	private String telephone;
	/**
	 * 详情
	 */
	private String detailUrl;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 营业时间
	 */
	private String shopHours;
	/**
	 * 大概评分
	 */
	private String overallRating;
	/**
	 * 味道评分
	 */
	private String tasteRating;
	/**
	 * 环境评分
	 */
	private String environmentRating;

}
