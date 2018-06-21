package com.tan.dream.sys.service;

import com.tan.dream.sys.domain.RoleDO;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
public interface RoleService {
	
	RoleDO get(Long roleId);
	
	List<RoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);

	List<RoleDO> list();
	List<RoleDO> list(Long userId);
}
