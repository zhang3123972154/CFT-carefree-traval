package com.xiaoyao.travel.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoyao.travel.common.entity.AiDialogue;
import com.xiaoyao.travel.common.entity.AiResponse;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 14:51
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 14:51
 * @updateRemark : 说明本次修改内容
 */
@Mapper
public interface AiResponseMapper extends BaseMapper<AiResponse> {
}
