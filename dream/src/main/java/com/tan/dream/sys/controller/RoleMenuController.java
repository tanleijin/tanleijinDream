package com.tan.dream.sys.controller;

import java.util.List;
import java.util.Map;

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

import com.tan.dream.sys.domain.RoleMenuDO;
import com.tan.dream.sys.service.RoleMenuService;
import com.tan.dream.core.utils.PageUtils;
import com.tan.dream.core.utils.Query;
import com.tan.dream.core.vo.ResultVO;

/**
 * 角色与菜单对应关系
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
 
@Controller
@RequestMapping("/sys/roleMenu")
public class RoleMenuController {
	@Autowired
	private RoleMenuService roleMenuService;
	
	@GetMapping()
	@RequiresPermissions("sys:roleMenu:roleMenu")
	String RoleMenu(){
	    return "sys/roleMenu/roleMenu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:roleMenu:roleMenu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoleMenuDO> roleMenuList = roleMenuService.list(query);
		int total = roleMenuService.count(query);
		PageUtils pageUtils = new PageUtils(roleMenuList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:roleMenu:add")
	String add(){
	    return "sys/roleMenu/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:roleMenu:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoleMenuDO roleMenu = roleMenuService.get(id);
		model.addAttribute("roleMenu", roleMenu);
	    return "sys/roleMenu/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:roleMenu:add")
	public ResultVO save( RoleMenuDO roleMenu){
		if(roleMenuService.save(roleMenu)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:roleMenu:edit")
	public ResultVO update( RoleMenuDO roleMenu){
		roleMenuService.update(roleMenu);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:roleMenu:remove")
	public ResultVO remove( Long id){
		if(roleMenuService.remove(id)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:roleMenu:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] ids){
		roleMenuService.batchRemove(ids);
		return ResultVO.ok();
	}
	
}
