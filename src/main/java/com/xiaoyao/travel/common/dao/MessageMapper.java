package com.xiaoyao.travel.common.dao;

import com.xiaoyao.travel.common.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 好友之间的聊天记录
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
	
}
