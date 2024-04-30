package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 旅行计划和景点对应的表，一个旅行计划中对应很多景点
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("plan_attraction")
public class PlanAttraction implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long planAttractionId;
	/**
	 * 旅行规划id
	 */
	private Long travelPlanId;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 景点id
	 */
	private Integer attractionId;
	/**
	 * 该规划在这个景点的详细推荐
	 */
	private String detail;

	private Date endTime;
	private Long nextNode;

	private Long nextDay;

}
