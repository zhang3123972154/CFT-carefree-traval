package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.AttractionPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 景点评论表，连接post_comment表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Mapper
public interface AttractionPostMapper extends BaseMapper<AttractionPost> {
	
}
