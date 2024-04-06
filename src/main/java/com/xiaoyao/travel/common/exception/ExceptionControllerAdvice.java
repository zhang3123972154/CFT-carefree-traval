package com.xiaoyao.travel.common.exception;

import com.xiaoyao.travel.common.vo.response.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/13 13:51
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/13 13:51
 * @updateRemark : 说明本次修改内容
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
  @ExceptionHandler(Exception.class)
  public ResponseBean handleException(Exception e){
    return ResponseBean.fail(e.getMessage());
  }
  @ExceptionHandler(ServiceException.class)
  public ResponseBean handleServiceException(ServiceException e) {
    return ResponseBean.fail(e.getResultCode(),e.getMessage());
  }
}
