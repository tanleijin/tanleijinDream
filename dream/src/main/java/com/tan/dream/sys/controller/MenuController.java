package com.tan.dream.sys.controller;

import java.util.List;
import java.util.Map;

import com.tan.dream.core.tree.Tree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tan.dream.sys.domain.MenuDO;
import com.tan.dream.sys.service.MenuService;
import com.tan.dream.core.utils.PageUtils;
import com.tan.dream.core.utils.Query;
import com.tan.dream.core.vo.ResultVO;

/**
 * 菜单管理
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
 
@Controller
@RequestMapping("/sys/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	@GetMapping()
	@RequiresPermissions("sys:menu:menu")
	String Menu(){
	    return "sys/menu/menu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:menu:menu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MenuDO> menuList = menuService.list(query);
		int total = menuService.count(query);
		PageUtils pageUtils = new PageUtils(menuList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:menu:add")
	String add(){
	    return "sys/menu/add";
	}

	@GetMapping("/edit/{menuId}")
	@RequiresPermissions("sys:menu:edit")
	String edit(@PathVariable("menuId") Long menuId,Model model){
		MenuDO menu = menuService.get(menuId);
		model.addAttribute("menu", menu);
	    return "sys/menu/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:menu:add")
	public ResultVO save( MenuDO menu){
		if(menuService.save(menu)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:edit")
	public ResultVO update( MenuDO menu){
		menuService.update(menu);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:menu:remove")
	public ResultVO remove( Long menuId){
		if(menuService.remove(menuId)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:menu:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] menuIds){
		menuService.batchRemove(menuIds);
		return ResultVO.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree() {
		Tree<MenuDO> tree = new Tree<MenuDO>();
		tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		Tree<MenuDO> tree = new Tree<MenuDO>();
		tree = menuService.getTree(roleId);
		return tree;
	}
}
