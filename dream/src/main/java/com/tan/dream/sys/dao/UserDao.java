package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:36
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long user_id);
	
	int batchRemove(Long[] userIds);
}
