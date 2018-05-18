package com.tan.dream.core.log.Service.impl;

import java.util.List;

import com.tan.dream.core.domain.PageDO;
import com.tan.dream.core.log.Service.LogService;
import com.tan.dream.core.log.dao.LogDao;
import com.tan.dream.core.log.domain.LogDO;
import com.tan.dream.core.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl implements LogService {
	@Autowired
	LogDao logMapper;

	@Async
	@Override
	public void save(LogDO logDO) {
		 logMapper.save(logDO);
	}

	@Override
	public PageDO<LogDO> queryList(Query query) {
		int total = logMapper.count(query);
		List<LogDO> logs = logMapper.list(query);
		PageDO<LogDO> page = new PageDO<>();
		page.setTotal(total);
		page.setRows(logs);
		return page;
	}

	@Override
	public int remove(Long id) {
		int count = logMapper.remove(id);
		return count;
	}

	@Override
	public int batchRemove(Long[] ids){
		return logMapper.batchRemove(ids);
	}
}
