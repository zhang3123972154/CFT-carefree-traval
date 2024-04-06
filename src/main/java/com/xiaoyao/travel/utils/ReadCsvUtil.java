package com.xiaoyao.travel.utils;

import com.google.common.base.Charsets;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : zhangxiaobo
 * @version : v1.0
 * @description : 一句话描述该类的功能
 * @createTime : 2024/4/4 17:41
 * @updateUser : zhangxiaobo
 * @updateTime : 2024/4/4 17:41
 * @updateRemark : 说明本次修改内容
 */

public class ReadCsvUtil {
  private String splitCharacter = ",";
  public List<String> getMetaData(File file) throws FileNotFoundException, UnsupportedEncodingException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
    return bufferedReader.lines().collect(Collectors.toList());
  }
}
