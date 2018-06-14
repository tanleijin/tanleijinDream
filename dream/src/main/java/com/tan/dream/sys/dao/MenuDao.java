package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.MenuDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
@Mapper
public interface MenuDao {

	MenuDO get(Long menuId);
	
	List<MenuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menu_id);
	
	int batchRemove(Long[] menuIds);

	List<String> listUserPerms(Long id);
	List<MenuDO> listMenuByUserId(Long id);
}
