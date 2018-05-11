package com.tan.dream.sys.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 角色与菜单对应关系
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
public class RoleMenuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//角色ID
	private Long roleId;
	//菜单ID
	private Long menuId;

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
	 * 设置：角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * 设置：菜单ID
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：菜单ID
	 */
	public Long getMenuId() {
		return menuId;
	}
}
