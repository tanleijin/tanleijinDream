package com.tan.dream.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.tan.dream.sys.dao.UserRoleDao;
import com.tan.dream.sys.domain.UserRoleDO;
import com.tan.dream.sys.service.UserRoleService;



@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public UserRoleDO get(Long id){
		return userRoleDao.get(id);
	}
	
	@Override
	public List<UserRoleDO> list(Map<String, Object> map){
		return userRoleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userRoleDao.count(map);
	}
	
	@Override
	public int save(UserRoleDO userRole){
		return userRoleDao.save(userRole);
	}
	
	@Override
	public int update(UserRoleDO userRole){
		return userRoleDao.update(userRole);
	}
	
	@Override
	public int remove(Long id){
		return userRoleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userRoleDao.batchRemove(ids);
	}
	
}
