package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.PostCommentLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 帖子的评论的点赞表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Mapper
public interface PostCommentLikeMapper extends BaseMapper<PostCommentLike> {
	
}
