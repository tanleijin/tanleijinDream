package com.tan.dream.sys.service;

import com.tan.dream.core.tree.Tree;
import com.tan.dream.sys.domain.DeptDO;
import com.tan.dream.sys.domain.UserDO;
import com.tan.dream.sys.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:36
 */
public interface UserService {
	
	UserDO get(Long userId);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);
	int batchremove(Long[] userIds);
	boolean exit(Map<String, Object> params);
	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<DeptDO> getTree();
}
