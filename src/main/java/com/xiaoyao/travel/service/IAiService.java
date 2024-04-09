package com.xiaoyao.travel.service;

import com.xiaoyao.travel.common.vo.request.SendToAiMsgVo;
import com.xiaoyao.travel.common.vo.response.DialogueHistoryVo;
import com.xiaoyao.travel.common.vo.response.ResponseBean;

import java.io.IOException;
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

public interface IAiService {
  ResponseBean sendMsg(SendToAiMsgVo sendToAiMsgVo);


  List<DialogueHistoryVo> getMsgHistory(Date startTime, Date endTime);

  String getOrcGeneral(String image);
}
