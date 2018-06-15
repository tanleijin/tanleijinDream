package com.tan.dream.sys.controller;

import com.tan.dream.common.constant.Constant;
import com.tan.dream.core.utils.PageUtils;
import com.tan.dream.core.utils.Query;
import com.tan.dream.core.vo.ResultVO;
import com.tan.dream.sys.domain.DeptDO;
import com.tan.dream.sys.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
 
@Controller
@RequestMapping("/sys/dept")
public class DeptController {

	private String prefix = "sys/dept/";

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("sys:dept:dept")
	String Dept(){
	    return prefix+"dept";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("sys:dept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		DeptDO sysDept = deptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			DeptDO parDept = deptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return  prefix + "edit";
	}

	//@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:dept:dept")
	public List<DeptDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<DeptDO> sysDeptList = deptService.list(query);
		return sysDeptList;
	}
	@GetMapping("/add")
	@RequiresPermissions("sys:dept:add")
	String add(){
	    return "sys/dept/add";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dept:add")
	public ResultVO save( DeptDO dept){
		if(deptService.save(dept)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:edit")
	public ResultVO update( DeptDO dept){
		deptService.update(dept);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:dept:remove")
	public ResultVO remove( Long deptId){
		if(deptService.remove(deptId)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:dept:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] deptIds){
		deptService.batchRemove(deptIds);
		return ResultVO.ok();
	}
	
}
