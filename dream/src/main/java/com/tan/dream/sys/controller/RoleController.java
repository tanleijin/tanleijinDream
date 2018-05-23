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

import com.tan.dream.sys.domain.RoleDO;
import com.tan.dream.sys.service.RoleService;
import com.tan.dream.core.utils.PageUtils;
import com.tan.dream.core.utils.Query;
import com.tan.dream.core.vo.ResultVO;

/**
 * 角色
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
 
@Controller
@RequestMapping("/sys/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	@RequiresPermissions("sys:role:role")
	String Role(){
	    return "sys/role/role";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:role:role")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoleDO> roleList = roleService.list(query);
		int total = roleService.count(query);
		PageUtils pageUtils = new PageUtils(roleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:role:add")
	String add(){
	    return "sys/role/add";
	}

	@GetMapping("/edit/{roleId}")
	@RequiresPermissions("sys:role:edit")
	String edit(@PathVariable("roleId") Long roleId,Model model){
		RoleDO role = roleService.get(roleId);
		model.addAttribute("role", role);
	    return "sys/role/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:role:add")
	public ResultVO save( RoleDO role){
		if(roleService.save(role)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:edit")
	public ResultVO update( RoleDO role){
		roleService.update(role);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:role:remove")
	public ResultVO remove( Long roleId){
		if(roleService.remove(roleId)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:role:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] roleIds){
		roleService.batchRemove(roleIds);
		return ResultVO.ok();
	}
	
}
