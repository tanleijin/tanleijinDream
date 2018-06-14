/**
 * 
 */
package com.tan.dream.common.generator.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author tanleijin
 * @Time 2018年9月6日
 * @description
 * 
 */
@Service
public interface GeneratorService {
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);
}
