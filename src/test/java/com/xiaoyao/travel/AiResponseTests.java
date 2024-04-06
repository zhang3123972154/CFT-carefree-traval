package com.xiaoyao.travel;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyao.travel.common.dao.PlanAttractionMapper;
import com.xiaoyao.travel.common.entity.PlanAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/6 18:09
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/6 18:09
 * @updateRemark : 说明本次修改内容
 */
@SpringBootTest
public class AiResponseTests {
  @Autowired
  private PlanAttractionMapper planAttractionMapper;
  @Test
  public void test() {
    String aiResponse = "{\"days\": {\"1\": {\"nodes\": {\"node1\": {\"place\": \"景点A\", \"time\": \"9:00-12:00\", \"Type\": \"景点\", \"description\": \"景点A的描述\", \"tag\": [\"自然风光\", \"历史文化\"], \"budget\": 100, \"telephone\": \"123456789\"}, \"node2\": {\"place\": \"饭店A\", \"time\": \"12:00-13:00\", \"Type\": \"饭店\", \"description\": \n" +
      "\"饭店A的描述\", \"tag\": [\"中餐\", \"西餐\"], \"budget\": 50, \"telephone\": \"987654321\"}, \"node3\": {\"place\": \"景点B\", \"time\": \"13:00-16:00\", \"Type\": \"景点\", \"description\": \"景点B的描述\", \"tag\": [\"艺术展览\", \"户外活动\"], \"budget\": 80, \"telephone\": \"456789123\"}, \"node4\": {\"place\": \"住宿A\", \"time\": \"16:00-次日9:00\", \"Type\": \"住宿\", \"description\": \"住宿A的描述\", \"tag\": [\"豪华酒店\", \"民宿\"], \"budget\": 200, \"telephone\": \"789123456\"}}}, \"2\": {\"nodes\": {\"node1\": {\"place\": \"景点C\", \"time\": \"9:00-12:00\", \"Type\": \"景点\", \"description\": \"景点C的描述\", \"tag\": [\"古迹\", \"风景名胜\"], \"budget\": 120, \"telephone\": \"321654987\"}, \"node2\": {\"place\": \"饭店B\", \"time\": \"12:00-13:00\", \"Type\": \"饭店\", \"description\": \"饭店B的描述\", \"tag\": [\"海鲜\", \"素食\"], \"budget\": 60, \"telephone\": \"654987321\"}, \"node3\": {\"place\": \"景点D\", \"time\": \"13:00-16:00\", \"Type\": \"景点\", \"description\": \"景点D的描述\", \"tag\": [\"动物园\", \"植物园\"], \"budget\": 90, \"telephone\": \"987321654\"}, \"node4\": {\"place\": \"交通A\", \"time\": \"16:00-17:00\", \"Type\": \"交通\", \"description\": \"交通A的描述\", \"tag\": [\"公交\", \"出租车\"], \"budget\": 20, \"telephone\": \"321987654\"}, \"node5\": {\"place\": \"住宿B\", \"time\": \"17:00-次日9:00\", \"Type\": \"住宿\", \"description\": \"住宿B的描述\", \"tag\": [\"度假村\", \"青年旅社\"], \"budget\": 150, \"telephone\": \"654321987\"}}}}}";
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
  /**
   *把拍平后的json进行格式化处理，输出标准的json格式
   * @param uglyJSONString
   * @return
   */
//  public static String jsonFormatter(String uglyJSONString){
//
//    Map<String,Object> map = new HashMap<>();
//    parseJson2Map(map,uglyJSONString,null);
//    JSONObject jsonObject = JSONObject.fromObject(map);
//    uglyJSONString = jsonObject.toString();
//
//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    JsonParser jp = new JsonParser();
//    JsonElement je = jp.parse(uglyJSONString);
//    String prettyJsonString = gson.toJson(je);
//    ///System.out.println(prettyJsonString);
//    return prettyJsonString;
//  }

}
