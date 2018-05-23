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

import com.tan.dream.sys.domain.UserPlusDO;
import com.tan.dream.sys.service.UserPlusService;
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
@RequestMapping("/sys/userPlus")
public class UserPlusController {
	@Autowired
	private UserPlusService userPlusService;
	
	@GetMapping()
	@RequiresPermissions("sys:userPlus:userPlus")
	String UserPlus(){
	    return "sys/userPlus/userPlus";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:userPlus:userPlus")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserPlusDO> userPlusList = userPlusService.list(query);
		int total = userPlusService.count(query);
		PageUtils pageUtils = new PageUtils(userPlusList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:userPlus:add")
	String add(){
	    return "sys/userPlus/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:userPlus:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserPlusDO userPlus = userPlusService.get(id);
		model.addAttribute("userPlus", userPlus);
	    return "sys/userPlus/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:userPlus:add")
	public ResultVO save( UserPlusDO userPlus){
		if(userPlusService.save(userPlus)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:userPlus:edit")
	public ResultVO update( UserPlusDO userPlus){
		userPlusService.update(userPlus);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:userPlus:remove")
	public ResultVO remove( Long id){
		if(userPlusService.remove(id)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:userPlus:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] ids){
		userPlusService.batchRemove(ids);
		return ResultVO.ok();
	}
	
}
