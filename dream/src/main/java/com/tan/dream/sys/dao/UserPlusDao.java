package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.UserPlusDO;

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
public interface UserPlusDao {

	UserPlusDO get(Long id);
	
	List<UserPlusDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserPlusDO userPlus);
	
	int update(UserPlusDO userPlus);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
