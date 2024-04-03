package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.Attraction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 景点列表，存放景点的位置等信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Mapper
public interface AttractionMapper extends BaseMapper<Attraction> {
	
}
