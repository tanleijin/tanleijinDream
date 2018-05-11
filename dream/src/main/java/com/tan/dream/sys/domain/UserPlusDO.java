package com.tan.dream.sys.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:36
 */
public class UserPlusDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long userId;
	//
	private Double payment;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	/**
	 * 获取：
	 */
	public Double getPayment() {
		return payment;
	}
}
