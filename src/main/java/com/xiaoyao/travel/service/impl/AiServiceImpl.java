package com.xiaoyao.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.dao.AiDialogueMapper;
import com.xiaoyao.travel.common.dao.PlanAttractionMapper;
import com.xiaoyao.travel.common.entity.AiDialogue;
import com.xiaoyao.travel.common.entity.PlanAttraction;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.common.vo.request.SendToAiMsgVo;
import com.xiaoyao.travel.common.vo.response.DialogueHistoryVo;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import com.xiaoyao.travel.service.IAiService;
import com.xiaoyao.travel.utils.BaiduOrcAuthUtil;
import com.xiaoyao.travel.utils.RequestUtil;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
  @Autowired
  private PlanAttractionMapper planAttractionMapper;
  static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();
  private String BAIDU_ORC_URL="https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token=";
  @Override
  public ResponseBean sendMsg(SendToAiMsgVo sendToAiMsgVo) {
    //校验参数
    //存数据库
    AiDialogue aiDialogue = BeanUtil.copyProperties(sendToAiMsgVo, AiDialogue.class);
    aiDialogueMapper.insert(aiDialogue);
    //发送给python，等待回复
    String aiResponse = "";
    //解析 ai返回的数据
    parseStrAndInsertData(aiResponse);
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

  @Override
  public String getOrcGeneral(String image) {
    ResponseBean auth = BaiduOrcAuthUtil.getAuth();
    if (auth.getSuccess().equals(0)) {
      throw new ServiceException(auth.getMessage());
    }
    if (image.contains("name=%22image%22%")) {
      String[] split = image.split("%0D%0A%0D%0A");
      image = split[1].split("%0D%0A")[0];
    }
    try {
      image = URLEncoder.encode(image, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new ServiceException("图片encode失败");
    }
    JSONObject data = JSONObject.parseObject(auth.getData().toString());
    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
    RequestBody body = RequestBody.create(mediaType, "url="+image+"&detect_direction=false&detect_language=false&vertexes_location=false&paragraph=false&probability=false");
    Request request = new Request.Builder()
      .url("https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token="+data.get("access_token"))
      .method("POST", body)
      .addHeader("Content-Type", "application/x-www-form-urlencoded")
      .addHeader("Accept", "application/json")
      .build();
    Response response = null;
    String result = "";
    try {
      response = HTTP_CLIENT.newCall(request).execute();
      result = response.body().string();
    } catch (IOException e) {
      throw new ServiceException(e.getMessage());
    }
    return result;
  }

  private void parseStrAndInsertData(String aiResponse) {
    Map jsonObject = JSON.parseObject(aiResponse);
    JSONObject days = (JSONObject)jsonObject.get("days");
    PlanAttraction planAttraction = new PlanAttraction();
    List<ArrayList<PlanAttraction>> insert = new ArrayList<>();
    for (String key : days.keySet()) {
      JSONObject nodes = (JSONObject) days.get(key);
      JSONObject subNodes = (JSONObject) nodes.get("nodes");
      ArrayList<PlanAttraction> planAttractions = new ArrayList<>();
      for (String node : subNodes.keySet()) {
        JSONObject node1 = (JSONObject) subNodes.get(node);
        StringBuilder sb = new StringBuilder();
        sb.append("\"type\":").append(node1.get("Type")).append(",").append("\"description\":").append(node1.get("description"))
          .append("\"tag\":").append(node1.get("tag")).append("\"budget\":").append(node1.get("budget"));
        planAttraction.setDetail(sb.toString());
        planAttraction.setTravelPlanId(1);
        planAttraction.setAttractionId(0);
        planAttractions.add(planAttraction);
      }
      insert.add(planAttractions);
    }
    insertPlanAttraction(insert);
  }

  public void insertPlanAttraction(List<ArrayList<PlanAttraction>> insert) {
    planAttractionMapper.insert(insert.get(0).get(0));
    for (int i = 1; i < insert.get(0).size(); i++) {
      planAttractionMapper.insert(insert.get(0).get(i));
      PlanAttraction planAttraction = insert.get(0).get(i - 1);
      planAttraction.setNextNode(insert.get(0).get(i).getPlanAttractionId());
      planAttractionMapper.updateById(planAttraction);
    }
    for (int i = 1; i < insert.size(); i++) {
      planAttractionMapper.insert(insert.get(i).get(0));
      for (int j = 1; j < insert.get(i).size(); j++) {
        planAttractionMapper.insert(insert.get(i).get(j));
        PlanAttraction planAttraction = insert.get(i).get(j - 1);
        planAttraction.setNextNode(insert.get(i).get(j).getPlanAttractionId());
        planAttractionMapper.updateById(planAttraction);
      }
      for (PlanAttraction planAttraction : insert.get(i - 1)) {
        planAttraction.setNextDay(insert.get(i).get(0).getPlanAttractionId());
        planAttractionMapper.updateById(planAttraction);
      }
    }
  }

}
