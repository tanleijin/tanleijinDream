package com.tan.dream.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tan.dream.sys.service.impl.UserServiceImpl;
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

import com.tan.dream.sys.domain.UserDO;
import com.tan.dream.sys.service.UserService;
import com.tan.dream.core.utils.PageUtils;
import com.tan.dream.core.utils.Query;
import com.tan.dream.core.vo.ResultVO;

/**
 * 
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:36
 */
 
@Controller
@RequestMapping("/sys/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping()
	@RequiresPermissions("sys:user:user")
	String User(){
	    return "sys/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("sys:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Map map = new HashMap();
		map.put("offset",0);
		map.put("limit",10);
		map.put("page",1);
		List<UserDO> userList = userService.list(map);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:user:add")
	String add(){
	    return "sys/user/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("sys:user:edit")
	String edit(@PathVariable("userId") Long userId,Model model){
		UserDO user = userService.get(userId);
		model.addAttribute("user", user);
	    return "sys/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:user:add")
	public ResultVO save( UserDO user){
		if(userService.save(user)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:edit")
	public ResultVO update( UserDO user){
		userService.update(user);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:user:remove")
	public ResultVO remove( Long userId){
		if(userService.remove(userId)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:user:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] userIds){
		userService.batchRemove(userIds);
		return ResultVO.ok();
	}
	
}
