package com.tan.dream.sys.service;

import com.tan.dream.sys.domain.RoleMenuDO;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
public interface RoleMenuService {
	
	RoleMenuDO get(Long id);
	
	List<RoleMenuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoleMenuDO roleMenu);
	
	int update(RoleMenuDO roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
