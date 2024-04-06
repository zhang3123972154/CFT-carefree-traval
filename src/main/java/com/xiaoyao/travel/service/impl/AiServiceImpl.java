package com.xiaoyao.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.dao.AiDialogueMapper;
import com.xiaoyao.travel.common.entity.AiDialogue;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.common.vo.request.SendToAiMsgVo;
import com.xiaoyao.travel.common.vo.response.DialogueHistoryVo;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import com.xiaoyao.travel.service.IAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 14:40
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 14:40
 * @updateRemark : 说明本次修改内容
 */
@Service
public class AiServiceImpl implements IAiService {
  @Autowired
  private AiDialogueMapper aiDialogueMapper;
  @Override
  public ResponseBean sendMsg(SendToAiMsgVo sendToAiMsgVo) {
    //校验参数
    //存数据库
    AiDialogue aiDialogue = BeanUtil.copyProperties(sendToAiMsgVo, AiDialogue.class);
    aiDialogueMapper.insert(aiDialogue);
    //发送给python，等待回复
    String aiResponse = "";
    //解析 ai返回的数据

    //存数据库

    //返回
    return null;
  }

  @Override
  public List<DialogueHistoryVo> getMsgHistory(Date startTime, Date endTime) {
    if (startTime.after(endTime)) {
      throw new ServiceException(ResultCode.PARAMETER_ERROR,"结束时间比开始时间早");
    }
    List<DialogueHistoryVo> list = aiDialogueMapper.getMsgHistory(startTime,endTime);
    return list;
  }

}
