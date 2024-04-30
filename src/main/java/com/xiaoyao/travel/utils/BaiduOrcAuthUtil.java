package com.xiaoyao.travel.utils;


import com.alibaba.fastjson.JSON;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 获取token类
 * @createTime : 2024/4/9 22:11
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/9 22:11
 * @updateRemark : 说明本次修改内容
 */
@Component
public class BaiduOrcAuthUtil {

  private static String apiKey;

  private static String secretKey;
  @Value("${app.orc.apiKey}")
  public void setApiKey(String apiKey) {
    BaiduOrcAuthUtil.apiKey=apiKey;
  }
  @Value("${app.orc.secretKey}")
  public void setSecretKey(String secretKey) {
    BaiduOrcAuthUtil.secretKey=secretKey;
  }

  /**
   * 获取权限token
   * @return 返回示例：
   * {
   * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
   * "expires_in": 2592000
   * }
   */
  public static ResponseBean getAuth() {
    // 官网获取的 API Key 更新为你注册的
    String clientId = apiKey;
    // 官网获取的 Secret Key 更新为你注册的
    String clientSecret = secretKey;
    String auth = getAuth(clientId, clientSecret);
    if (auth == null) {
      return ResponseBean.fail("获取token失败！");
    }
    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(auth);
    if (jsonObject.containsKey("error")) {
      return ResponseBean.fail(auth);
    }else {
      return ResponseBean.success(auth);
    }
  }

  /**
   * 获取API访问token
   * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
   * @param ak - 百度云官网获取的 API Key
   * @param sk - 百度云官网获取的 Securet Key
   * @return assess_token 示例：
   * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
   */
  private static String getAuth(String ak, String sk) {
    // 获取token地址
    String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
    String getAccessTokenUrl = authHost
      // 1. grant_type为固定参数
      + "grant_type=client_credentials"
      // 2. 官网获取的 API Key
      + "&client_id=" + ak
      // 3. 官网获取的 Secret Key
      + "&client_secret=" + sk;

    try {
      URL realUrl = new URL(getAccessTokenUrl);
      // 打开和URL之间的连接
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();
      // 获取所有响应头字段
      Map<String, List<String>> map = connection.getHeaderFields();
      // 遍历所有的响应头字段
      for (String key : map.keySet()) {
        System.err.println(key + "--->" + map.get(key));
      }
      // 定义 BufferedReader输入流来读取URL的响应
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String result = "";
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
      /**
       * 返回结果示例
       */
//      System.err.println("result:" + result);
//      JSONObject jsonObject = new JSONObject(result);
//      String access_token = jsonObject.getString("access_token");
      return result;
    } catch (Exception e) {
      System.err.printf("获取token失败！");
      e.printStackTrace(System.err);
    }
    return null;
  }
}
