package com.xiaoyao.travel.config;

import cn.hutool.json.JSONUtil;
import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.vo.response.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 拦截未登录请求
 * @createTime : 2023/4/17 8:03
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/4/17 8:03
 * @updateRemark :
 */
@Slf4j
@Configuration
public class LoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    System.out.println(e.getMessage());
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    PrintWriter out = httpServletResponse.getWriter();
    out.write(JSONUtil.toJsonStr(ResponseBean.fail(String.valueOf(ResultCode.MISS_TOKEN))));
    out.flush();
    out.close();
  }
}
