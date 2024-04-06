package com.xiaoyao.travel.common.exception;


import com.xiaoyao.travel.common.constants.ResultCode;
import com.xiaoyao.travel.common.vo.response.ResponseBean;

/**
 * @author sannmizu
 * @date 2020/6/14 16:35
 */
public class ServiceException extends RuntimeException {
    private ResultCode resultCode;
    private String message;

    public ServiceException() {
        this.resultCode = ResultCode.ERROR;
    }
    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ServiceException(String message, ResultCode resultCode) {
        this.message = message;
        this.resultCode = resultCode;
    }
    public ServiceException(String message) {
        this.message = message;
        this.resultCode = ResultCode.ERROR;
    }
    public ServiceException(ResultCode resultCode,String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
    @Override
    public String getMessage(){
        return message;
    }
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResponseBean getFailResponse() {
        if (null != getMessage() && !getMessage().isEmpty()) {
            return ResponseBean.fail(resultCode.getCode(), getMessage());
        }
        return ResponseBean.fail(String.valueOf(resultCode));
    }
}