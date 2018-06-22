package com.tan.dream.sys.service.impl;

import com.tan.dream.sys.dao.RoleMenuDao;
import com.tan.dream.sys.dao.UserRoleDao;
import com.tan.dream.sys.domain.RoleMenuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.tan.dream.sys.dao.RoleDao;
import com.tan.dream.sys.domain.RoleDO;
import com.tan.dream.sys.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RoleMenuDao roleMenuDao;


	@Override
	public RoleDO get(Long roleId){
		return roleDao.get(roleId);
	}
	
	@Override
	public List<RoleDO> list(Map<String, Object> map){
		return roleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roleDao.count(map);
	}
	
	@Override
	public int save(RoleDO role){
		return roleDao.save(role);
	}
	

	@Override
	public int update(RoleDO role) {
		int r = roleDao.update(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		roleMenuDao.removeByRoleId(roleId);
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		if (rms.size() > 0) {
			roleMenuDao.batchSave(rms);
		}
		return r;
	}
	@Override
	public int remove(Long roleId){
		return roleDao.remove(roleId);
	}
	
	@Override
	public int batchRemove(Long[] roleIds){
		return roleDao.batchRemove(roleIds);
	}

	@Override
	public List<RoleDO> list() {
		List<RoleDO> roles = roleDao.list(new HashMap<>(16));
		return roles;
	}

	@Override
	public List<RoleDO> list(Long userId) {
		List<Long> rolesIds = userRoleDao.listRoleId(userId);
		List<RoleDO> roles = roleDao.list(new HashMap<>(16));
		for (RoleDO roleDO : roles) {
			roleDO.setRoleSign("false");
			for (Long roleId : rolesIds) {
				if (Objects.equals(roleDO.getRoleId(), roleId)) {
					roleDO.setRoleSign("true");
					break;
				}
			}
		}
		return roles;
	}

}
