package com.xiaoyao.travel.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoyao.travel.common.entity.PostComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 帖子评论列表，存储评论的主要信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Mapper
public interface PostCommentMapper extends BaseMapper<PostComment> {
	
}
