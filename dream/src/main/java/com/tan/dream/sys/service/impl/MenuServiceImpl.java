package com.tan.dream.sys.service.impl;

import com.tan.dream.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.tan.dream.sys.dao.MenuDao;
import com.tan.dream.sys.domain.MenuDO;
import com.tan.dream.sys.service.MenuService;



@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public MenuDO get(Long menuId){
		return menuDao.get(menuId);
	}
	
	@Override
	public List<MenuDO> list(Map<String, Object> map){
		return menuDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return menuDao.count(map);
	}
	
	@Override
	public int save(MenuDO menu){
		return menuDao.save(menu);
	}
	
	@Override
	public int update(MenuDO menu){
		return menuDao.update(menu);
	}
	
	@Override
	public int remove(Long menuId){
		return menuDao.remove(menuId);
	}
	
	@Override
	public int batchRemove(Long[] menuIds){
		return menuDao.batchRemove(menuIds);
	}
	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuDao.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}
	
}
