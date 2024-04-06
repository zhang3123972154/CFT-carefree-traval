package com.xiaoyao.travel;

import com.xiaoyao.travel.utils.ReadCsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
@Slf4j
class TravelApplicationTests {
  String jdbcUser = "root";
  String jdbcPassword = "abcswhu2021";
  String url = "jdbc:mysql://43.139.8.195:3306/xiaoyao?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
  HashSet<Integer> ignoreIndex = new HashSet<Integer>(){{
    add(0);
    add(8);
    add(9);
    add(10);
    add(14);
    add(20);
    add(22);
    add(23);
    add(24);
    add(25);
    add(26);
    add(27);
    add(28);
    add(29);
    add(30);
    add(31);
    add(32);
    add(33);
    add(34);
    add(35);
  }};
  String packageName = "C:\\Users\\zhangxiaobo\\Documents\\WeChat Files\\wxid_vj57ohrln7vz22\\FileStorage\\File\\2024-04\\wuhanminist";
  ArrayList<File> files = new ArrayList<>();
  String splitCharacter = ",";
  static org.slf4j.Logger logger = LoggerFactory.getLogger("test");
  @Test
  void contextLoads() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = DriverManager.getConnection(url, jdbcUser, jdbcPassword);
    connection.setAutoCommit(false);
    String sql = "insert into attraction values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement pstm = connection.prepareStatement(sql);
    ReadCsvUtil readCsvUtil = new ReadCsvUtil();
    org.slf4j.Logger test = LoggerFactory.getLogger("test");
    File file = new File(packageName);

    for (File listFile : file.listFiles()) {
      if (listFile.isDirectory()) {
        getFiles(listFile);
      }else {
        String[] split = listFile.getName().split("\\.");
        if (split[split.length-1].equals("csv")) {
          files.add(listFile);
        }
      }
    }
    for (File targetFile : files) {
      try {
        List<String> metaData = readCsvUtil.getMetaData(targetFile);
        logger.info(targetFile.getName()+" start");
        insertInto(metaData,connection,pstm);
        logger.info(targetFile.getName()+" end");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
    }
  }
  void getFiles(File file) {
    if (!file.isDirectory()) {
      return;
    }
    for (File listFile : file.listFiles()) {
      String[] split = listFile.getName().split("\\.");
      if (split[split.length-1].equals("csv")) {
        files.add(listFile);
      }
    }
  }
  void insertInto(List<String> metaData,Connection connection,PreparedStatement pstm) throws SQLException {
    for (int i = 1; i < metaData.size(); i++) {
      String[] split = metaData.get(i).split(splitCharacter);
      pstm.setString(1,split[1]);
      pstm.setString(2,split[2]);
      pstm.setString(3,split[3]);
      pstm.setString(4,split[4]);
      pstm.setString(5,split[5]);
      pstm.setString(6,split[6]);
      pstm.setString(7,split[7]);
      pstm.setDouble(8,Double.parseDouble(split[11]));
      pstm.setDouble(9,Double.parseDouble(split[12]));
      pstm.setString(10,split[13]);
      pstm.setString(11,split[15]);
      pstm.setString(12,split[16]);
      pstm.setString(13,split[17]);
      pstm.setString(14,split[18]);
      pstm.setString(15,split[19]);
      pstm.setString(16,split[21]);
      pstm.addBatch();
    }
    pstm.executeBatch();
    connection.commit();
  }

}
