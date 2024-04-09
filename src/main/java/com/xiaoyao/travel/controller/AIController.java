package com.xiaoyao.travel.controller;

import com.xiaoyao.travel.common.vo.request.SendToAiMsgVo;
import com.xiaoyao.travel.common.vo.response.DialogueHistoryVo;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import com.xiaoyao.travel.service.IAiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 14:33
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 14:33
 * @updateRemark : 说明本次修改内容
 */
@RestController
@RequestMapping("/ai")
public class AIController {

  @Autowired
  private IAiService aiService;
  @PostMapping("/send")
  @ApiOperation(value = "向ai发送消息",httpMethod = "POST")
  public ResponseBean sendMsg(@RequestBody SendToAiMsgVo sendToAiMsgVo) {
    return ResponseBean.success(aiService.sendMsg(sendToAiMsgVo));
  }
  @GetMapping("/history")
  @ApiOperation(value = "获取消息记录",httpMethod = "GET")
  public ResponseBean<List<DialogueHistoryVo>> getMsgHistory(@RequestParam Date startTime, @RequestParam Date endTime) {
    return ResponseBean.success(aiService.getMsgHistory(startTime,endTime));
  }
  @PostMapping("/orc/general")
  @ApiOperation(value = "调用百度api识别图片文字",httpMethod = "POST")
  public String getOrcGeneral(@RequestBody String image) {
    return aiService.getOrcGeneral(image);
  }

}
