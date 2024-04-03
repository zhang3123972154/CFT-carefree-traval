package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.PlanAttraction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 旅行计划和景点对应的表，一个旅行计划中对应很多景点
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Mapper
public interface PlanAttractionMapper extends BaseMapper<PlanAttraction> {
	
}
