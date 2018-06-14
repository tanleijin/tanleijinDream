package com.tan.dream.sys.service;

import com.tan.dream.core.tree.Tree;
import com.tan.dream.sys.domain.MenuDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单管理
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
public interface MenuService {

	List<Tree<MenuDO>> listMenuTree(Long id);

	MenuDO get(Long menuId);
	
	List<MenuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);

	Set<String> listPerms(Long userId);
}
