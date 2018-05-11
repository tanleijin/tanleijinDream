package com.tan.dream.sys.dao;

import com.tan.dream.sys.domain.DictDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
@Mapper
public interface DictDao {

	DictDO get(Long id);
	
	List<DictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictDO dict);
	
	int update(DictDO dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
