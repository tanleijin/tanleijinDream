package com.tan.dream.sys.service.impl;

import com.tan.dream.core.tree.BuildTree;
import com.tan.dream.core.tree.Tree;
import com.tan.dream.core.utils.MD5Utils;
import com.tan.dream.sys.dao.DeptDao;
import com.tan.dream.sys.dao.UserRoleDao;
import com.tan.dream.sys.domain.DeptDO;
import com.tan.dream.sys.vo.UserVO;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.tan.dream.sys.dao.UserDao;
import com.tan.dream.sys.domain.UserDO;
import com.tan.dream.sys.service.UserService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private DeptDao deptDao;




	@Override
	public UserDO get(Long userId){
		return userDao.get(userId);
	}
	
	@Override
	public List<UserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO user){
		return userDao.save(user);
	}
	
	@Override
	public int update(UserDO user){
		return userDao.update(user);
	}
	
	@Override
	public int remove(Long userId){
		return userDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Long[] userIds){
		return userDao.batchRemove(userIds);
	}
	@Override
	public int updatePersonal(UserDO userDO) {
		return userDao.update(userDO);
	}
	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userDao.batchRemove(userIds);
		userRoleDao.batchRemoveByUserId(userIds);
		return count;
	}
	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = userDao.list(params).size() > 0;
		return exit;
	}
	@Override
	public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
		if(Objects.equals(userVO.getUserDO().getUserId(),userDO.getUserId())){
			if(Objects.equals(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdOld()),userDO.getPassword())){
				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdNew()));
				return userDao.update(userDO);
			}else{
				throw new Exception("输入的旧密码有误！");
			}
		}else{
			throw new Exception("你修改的不是你登录的账号！");
		}
	}
	@Override
	public int adminResetPwd(UserVO userVO) throws Exception {
		UserDO userDO =get(userVO.getUserDO().getUserId());
		if("admin".equals(userDO.getUsername())){
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
		return userDao.update(userDO);


	}
	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> depts = deptDao.list(new HashMap<String, Object>(16));
		Long[] pDepts = deptDao.listParentDept();
		Long[] uDepts = userDao.listAllDept();
		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
		for (DeptDO dept : depts) {
			if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
				continue;
			}
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "dept");
			tree.setState(state);
			trees.add(tree);
		}
		List<UserDO> users = userDao.list(new HashMap<String, Object>(16));
		for (UserDO user : users) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(user.getUserId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "user");
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
}
