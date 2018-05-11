package com.tan.dream.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.tan.dream.sys.dao.UserPlusDao;
import com.tan.dream.sys.domain.UserPlusDO;
import com.tan.dream.sys.service.UserPlusService;



@Service
public class UserPlusServiceImpl implements UserPlusService {
	@Autowired
	private UserPlusDao userPlusDao;
	
	@Override
	public UserPlusDO get(Long id){
		return userPlusDao.get(id);
	}
	
	@Override
	public List<UserPlusDO> list(Map<String, Object> map){
		return userPlusDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userPlusDao.count(map);
	}
	
	@Override
	public int save(UserPlusDO userPlus){
		return userPlusDao.save(userPlus);
	}
	
	@Override
	public int update(UserPlusDO userPlus){
		return userPlusDao.update(userPlus);
	}
	
	@Override
	public int remove(Long id){
		return userPlusDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userPlusDao.batchRemove(ids);
	}
	
}
