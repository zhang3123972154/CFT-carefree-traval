package com.xiaoyao.travel.service;


import com.xiaoyao.travel.common.vo.response.IntegerAndStringVo;
import com.xiaoyao.travel.common.vo.response.LoginResponseVo;
import com.xiaoyao.travel.common.vo.response.PageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2023/7/11 20:04
 * @updateUser : zhangxiaobo
 * @updateTime : 2023/7/11 20:04
 * @updateRemark : 说明本次修改内容
 */

public interface IUserService {
  void register(String email, String password,String verification);

  void sendEmailCode(String email, Integer purposeIdentify);

  LoginResponseVo loginByPassword(String username, String password, String purpose);

  LoginResponseVo refreshToken(String refreshToken);


    void uploadIndividual(List<String> individual);

  PageDTO<IntegerAndStringVo> searchIndividual(String keyword, Integer pageSize, Integer pageNum);

    List<IntegerAndStringVo> randomIndividual();


}
