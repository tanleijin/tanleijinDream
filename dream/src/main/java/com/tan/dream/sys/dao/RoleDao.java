package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.RoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
@Mapper
public interface RoleDao {

	RoleDO get(Long roleId);
	
	List<RoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Long role_id);
	
	int batchRemove(Long[] roleIds);
}
