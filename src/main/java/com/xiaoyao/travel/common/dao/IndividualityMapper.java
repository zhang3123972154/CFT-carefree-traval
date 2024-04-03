package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.Individuality;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户个性表，存储用户的个性标签
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Mapper
public interface IndividualityMapper extends BaseMapper<Individuality> {
	
}
