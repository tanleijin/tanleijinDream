package com.tan.dream.sys.service.impl;

import com.tan.dream.core.tree.BuildTree;
import com.tan.dream.core.tree.Tree;
import com.tan.dream.core.utils.StringUtils;
import com.tan.dream.sys.dao.RoleMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

import com.tan.dream.sys.dao.MenuDao;
import com.tan.dream.sys.domain.MenuDO;
import com.tan.dream.sys.service.MenuService;



@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleMenuDao roleMenuDao;


	@Override
	public MenuDO get(Long menuId){
		return menuDao.get(menuId);
	}
	
	@Override
	public List<MenuDO> list(Map<String, Object> map){
		return menuDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return menuDao.count(map);
	}
	
	@Override
	public int save(MenuDO menu){
		return menuDao.save(menu);
	}
	
	@Override
	public int update(MenuDO menu){
		return menuDao.update(menu);
	}
	
	@Override
	public int remove(Long menuId){
		return menuDao.remove(menuId);
	}
	
	@Override
	public int batchRemove(Long[] menuIds){
		return menuDao.batchRemove(menuIds);
	}
	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuDao.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}
	@Override
	@Cacheable(cacheNames = "menu")
	public List<Tree<MenuDO>> listMenuTree(Long id) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.listMenuByUserId(id);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
		return list;
	}

	@Override
	public Tree<MenuDO> getTree() {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.list(new HashMap<>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<MenuDO> getTree(Long id) {
		// 根据roleId查询权限
		List<MenuDO> menus = menuDao.list(new HashMap<String, Object>(16));
		List<Long> menuIds = roleMenuDao.listMenuIdByRoleId(id);
		List<Long> temp = menuIds;
		for (MenuDO menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.list(new HashMap<String, Object>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

}
