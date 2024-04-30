package com.xiaoyao.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.dao.AiDialogueMapper;
import com.xiaoyao.travel.common.dao.AiResponseMapper;
import com.xiaoyao.travel.common.dao.PlanAttractionMapper;
import com.xiaoyao.travel.common.entity.AiDialogue;
import com.xiaoyao.travel.common.entity.AiResponse;
import com.xiaoyao.travel.common.entity.PlanAttraction;
import com.xiaoyao.travel.common.exception.ServiceException;
import com.xiaoyao.travel.common.vo.request.SendToAiMsgVo;
import com.xiaoyao.travel.common.vo.response.DialogueHistoryVo;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import com.xiaoyao.travel.service.IAiService;
import com.xiaoyao.travel.utils.BaiduOrcAuthUtil;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
  @Autowired
  private AiResponseMapper aiResponseMapper;
  private String BAIDU_ORC_URL="https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token=";
  @Override
  @Transactional
  public Object sendMsg(SendToAiMsgVo sendToAiMsgVo) {
    //校验参数
    //存数据库
    AiDialogue aiDialogue = BeanUtil.copyProperties(sendToAiMsgVo, AiDialogue.class);
    aiDialogueMapper.insert(aiDialogue);
    //发送给python，等待回复
    String aiResponse = "{\"Day1\": {\"node1\": {\"id\": \"B0JKP7VC0E\", \"name\": \"小食侯湘(武汉旗舰店)\", \"start_time\": \"10:00\", \"end_time\": \"12:00\", \"cost\": \"50\", \"rating\": \"4.5\", \"type\": \"餐饮服务;中餐厅;湖南菜(湘菜)\", \"photo\": \"http://store.is.autonavi.com/showpic/c9f477d537e8c9ec6d83644850d2e583\", \"address\": \"中南欢乐汇A座文安街与丁字桥路交叉口西120米\"}, \"node2\": {\"id\": \"B0JGAAFH35\", \"name\": \"食钵记湘菜馆\", \"start_time\": \"12:30\", \"end_time\": \"14:00\", \"cost\": \"50\", \"rating\": \"4.4\", \"type\": \"餐饮服务;餐饮相关场所;餐饮相关\", \"photo\": \"http://s-pic.oss-cn-beijing.aliyuncs.com/desensitize/images/publish/72def6c5f5ad62055e222abcebda0096.jpg\", \"address\": \"二七街街道二七路131号\"}}, \"Day2\": {\"node1\": {\"id\": \"B0FFHGNCL5\", \"name\": \"肥肥湘菜馆(天后社区店)\", \"start_time\": \"10:00\", \"end_time\": \"12:00\", \"cost\": \"102\", \"rating\": \"4.3\", \"type\": \"餐饮服务;中餐厅;湖南菜(湘菜)\", \"photo\": \"http://store.is.autonavi.com/showpic/51bfcfd8be6b254e8dba8f3d2a3beb84\", \"address\": \"友谊路128附1号附近\"}, \"node2\": {\"id\": \"B001B1H29G\", \"name\": \"湘菜馆\", \"start_time\": \"12:30\", \"end_time\": \"14:00\", \"cost\": \"40\", \"rating\": \"4.2\", \"type\": \"餐饮服务;中餐厅;湖南菜(湘菜)\", \"photo\": \"http://store.is.autonavi.com/showpic/30759ae81e1bc9f2010f61064187f6d3\", \"address\": \"武汉大学工学部教工40舍20号\"}}, \"Day3\": {\"node1\": {\"id\": \"B0IATALZL3\", \"name\": \"湘菜美食烧烤\", \"start_time\": \"10:00\", \"end_time\": \"12:00\", \"cost\": \"40\", \"rating\": \"4.1\", \"type\": \"餐饮服务;中餐厅;湖南菜(湘菜)\", \"photo\": \"http://store.is.autonavi.com/showpic/2194b45140c6f82601a29e0b6c48f5aa\", \"address\": \"卓刀泉街关公社区东北1门旁\"}, \"node2\": {\"id\": \"B0J2376PAZ\", \"name\": \"食钵记湘菜馆(盘龙城店)\", \"start_time\": \"12:30\", \"end_time\": \"14:00\", \"cost\": \"63\", \"rating\": \"4.2\", \"type\": \"餐饮服务;中餐厅;湖南菜(湘菜)\", \"photo\": \"http://store.is.autonavi.com/showpic/da677952e91e8ac937876f5cdee7897a\", \"address\": \"盘龙大道44号\"}}}";
    //解析 ai返回的数据
    parseStrAndInsertData(aiResponse);
    //存数据库
    AiDialogue sender = new AiDialogue();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    sender.setSendTime(sendToAiMsgVo.getSendTime());
    sender.setMessage(sendToAiMsgVo.getMessage());
    String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    sender.setUserId(Long.valueOf(userId));

    AiResponse responser = new AiResponse();
    responser.setContent(aiResponse);
    responser.setResponseTime(new Date());
    responser.setDialogueId(aiDialogue.getDialogueId());
    responser.setAiId(0);
    aiResponseMapper.insert(responser);
    //返回
    return JSON.parseObject(aiResponse);
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
  public List<String> getOrcGeneral(String image) {
    ResponseBean auth = BaiduOrcAuthUtil.getAuth();
    if (auth.getSuccess().equals(0)) {
      throw new ServiceException(auth.getMessage());
    }
    String result = "";
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
    RequestBody body = RequestBody.create(mediaType, "image="+image+"&detect_direction=false&detect_language=false&vertexes_location=false&paragraph=false&probability=false");
    Request request = new Request.Builder()
            .url("https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token="+data.get("access_token"))
            .method("POST", body)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Accept", "application/json")
            .build();
    Response response = null;

    try {
      response = HTTP_CLIENT.newCall(request).execute();
      result = response.body().string();
    } catch (IOException e) {
      throw new ServiceException(e.getMessage());
    }
    return Arrays.asList(result);
  }

  private void parseStrAndInsertData(String aiResponse) {
    JSONObject jsonObject = JSON.parseObject(aiResponse);
    PlanAttraction planAttraction = new PlanAttraction();
    List<ArrayList<PlanAttraction>> insert = new ArrayList<>();
    for (String key : jsonObject.keySet()) {
      JSONObject nodes = (JSONObject) jsonObject.get(key);
      ArrayList<PlanAttraction> planAttractions = new ArrayList<>();
      for (String node : nodes.keySet()) {
        JSONObject node1 = (JSONObject) nodes.get(node);
        StringBuilder sb = new StringBuilder();
        sb.append(node1);
//        sb.append("\"type\":").append(node1.get("Type")).append(",").append("\"description\":").append(node1.get("description"))
//                .append("\"tag\":").append(node1.get("tag")).append("\"budget\":").append(node1.get("budget"));
        planAttraction.setDetail(sb.toString());
        planAttraction.setTravelPlanId(1L);
        planAttraction.setAttractionId(0);
        planAttractions.add(planAttraction);
      }
      insert.add(planAttractions);
    }
    insertPlanAttraction(insert);
  }

  @Transactional
  public void insertPlanAttraction(List<ArrayList<PlanAttraction>> insert) {
    planAttractionMapper.insert(insert.get(0).get(0));
    for (int i = 1; i < insert.get(0).size(); i++) {
      insert.get(0).get(i).setPlanAttractionId(null);
      planAttractionMapper.insert(insert.get(0).get(i));
      PlanAttraction planAttraction = insert.get(0).get(i - 1);
      planAttraction.setNextNode(insert.get(0).get(i).getPlanAttractionId());
      planAttractionMapper.updateById(planAttraction);
    }
    for (int i = 1; i < insert.size(); i++) {
      insert.get(i).get(0).setPlanAttractionId(null);
      planAttractionMapper.insert(insert.get(i).get(0));
      for (int j = 1; j < insert.get(i).size(); j++) {
        insert.get(i).get(j).setPlanAttractionId(null);
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
