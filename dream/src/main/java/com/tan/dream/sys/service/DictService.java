package com.tan.dream.sys.service;

import com.tan.dream.sys.domain.DictDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
public interface DictService {
	
	DictDO get(Long id);
	
	List<DictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictDO dict);
	
	int update(DictDO dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
