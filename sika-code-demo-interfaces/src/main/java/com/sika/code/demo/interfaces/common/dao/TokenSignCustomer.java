package com.sika.code.demo.interfaces.common.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * Token 签名 Model
 *
 * 挂在到SaSession上的token签名
 *
 * @author kong
 *
 */
@Data
public class TokenSignCustomer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1406115065849845073L;

	/**
	 * token值
	 */
	private String value;

	/**
	 * 所属设备类型
	 */
	private String device;


}
