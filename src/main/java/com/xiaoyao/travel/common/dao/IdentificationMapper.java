package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.Identification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 实名认证表，存储实名认证信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Mapper
public interface IdentificationMapper extends BaseMapper<Identification> {
	
}
