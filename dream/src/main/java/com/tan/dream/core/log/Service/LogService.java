package com.tan.dream.core.log.Service;

import java.util.List;

import com.tan.dream.core.domain.PageDO;
import com.tan.dream.core.log.domain.LogDO;
import com.tan.dream.core.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
