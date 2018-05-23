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

import com.tan.dream.sys.domain.UserRoleDO;
import com.tan.dream.sys.service.UserRoleService;
import com.tan.dream.core.utils.PageUtils;
import com.tan.dream.core.utils.Query;
import com.tan.dream.core.vo.ResultVO;

/**
 * 用户与角色对应关系
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:36
 */
 
@Controller
@RequestMapping("/sys/userRole")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping()
	@RequiresPermissions("sys:userRole:userRole")
	String UserRole(){
	    return "sys/userRole/userRole";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:userRole:userRole")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserRoleDO> userRoleList = userRoleService.list(query);
		int total = userRoleService.count(query);
		PageUtils pageUtils = new PageUtils(userRoleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:userRole:add")
	String add(){
	    return "sys/userRole/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:userRole:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserRoleDO userRole = userRoleService.get(id);
		model.addAttribute("userRole", userRole);
	    return "sys/userRole/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:userRole:add")
	public ResultVO save( UserRoleDO userRole){
		if(userRoleService.save(userRole)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:userRole:edit")
	public ResultVO update( UserRoleDO userRole){
		userRoleService.update(userRole);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:userRole:remove")
	public ResultVO remove( Long id){
		if(userRoleService.remove(id)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:userRole:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] ids){
		userRoleService.batchRemove(ids);
		return ResultVO.ok();
	}
	
}
