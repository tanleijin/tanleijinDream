package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.DeptDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 部门管理
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
@Mapper
public interface DeptDao {

	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long dept_id);
	
	int batchRemove(Long[] deptIds);
}
