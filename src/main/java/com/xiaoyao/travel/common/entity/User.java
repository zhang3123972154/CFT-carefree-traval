package com.xiaoyao.travel.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.apache.catalina.valves.RemoteIpValve;

/**
 * 用户表，存储用户的基本信息
 * 
 * @author zhangxiaobo
 * @email 2417160223@qq.com
 * @date 2024-03-15 10:35:34
 */
@Data
@TableName("user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户表id
	 */
	@TableId
	private Long userId;
	/**
	 * 用户昵称
	 */
	private String userName;
	/**
	 * 用户性别
	 */
	private Integer sex;
	/**
	 * identification表主键
	 */
	private Integer identificationId;
	/**
	 * 用户手机号
	 */
	private String phone;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 通讯地址
	 */
	private String mailingAddress;
	/**
	 * 
	 */
	private String avatar;
	/**
	 * 
	 */
	private String organization;
	/**
	 * 是否被删除，0表示未被删除，1表示被删除
	 */
	private Integer isDeleted;

	private String password;

	private Integer status;

}
