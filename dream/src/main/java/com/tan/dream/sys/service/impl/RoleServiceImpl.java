package com.tan.dream.sys.service.impl;

import com.tan.dream.sys.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.tan.dream.sys.dao.RoleDao;
import com.tan.dream.sys.domain.RoleDO;
import com.tan.dream.sys.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
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
	public int update(RoleDO role){
		return roleDao.update(role);
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
