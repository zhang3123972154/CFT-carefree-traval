package com.xiaoyao.travel.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoyao.travel.common.entity.AiDialogue;
import com.xiaoyao.travel.common.vo.response.DialogueHistoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * 和ai对话记录列表
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:35
 */
@Mapper
public interface AiDialogueMapper extends BaseMapper<AiDialogue> {

  List<DialogueHistoryVo> getMsgHistory(Date startTime, Date endTime);
}
