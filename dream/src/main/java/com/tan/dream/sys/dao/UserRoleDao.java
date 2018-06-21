package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.UserRoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:36
 */
@Mapper
public interface UserRoleDao {

	UserRoleDO get(Long id);
	
	List<UserRoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserRoleDO userRole);
	
	int update(UserRoleDO userRole);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 通过userId获取该用户拥有的所有角色
	 * @param userId
	 * @return
	 */
	List<Long> listRoleId(Long userId);

	int batchRemoveByUserId(Long[] ids);

}
