package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 生成的旅行计划表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("travel_plan")
public class TravelPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 表id
	 */
	@TableId
	private Integer travelPlanId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 计划景点表id
	 */
	private Integer planAttractionId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新者
	 */
	private Integer updateUserId;
	/**
	 * 删除时间
	 */
	private Date deletedTime;
	/**
	 * 1表示已被删除，0表示未被删除
	 */
	@TableLogic
	private Integer isDeleted;

}
