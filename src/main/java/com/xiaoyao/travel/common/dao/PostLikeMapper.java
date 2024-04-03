package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.PostLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 帖子点赞表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Mapper
public interface PostLikeMapper extends BaseMapper<PostLike> {
	
}
