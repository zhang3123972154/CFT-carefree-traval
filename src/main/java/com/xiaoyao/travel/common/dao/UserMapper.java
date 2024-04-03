package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表，存储用户的基本信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	
}
