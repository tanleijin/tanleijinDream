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

import com.tan.dream.sys.domain.DictDO;
import com.tan.dream.sys.service.DictService;
import com.tan.dream.common.utils.PageUtils;
import com.tan.dream.common.utils.Query;
import com.tan.dream.common.vo.ResultVO;

/**
 * 字典表
 * 
 * @author tanleijin
 * @email tanleijin@163.com
 * @date 2018-05-11 16:38:35
 */
 
@Controller
@RequestMapping("/sys/dict")
public class DictController {
	@Autowired
	private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("sys:dict:dict")
	String Dict(){
	    return "sys/dict/dict";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:dict:dict")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DictDO> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:dict:add")
	String add(){
	    return "sys/dict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:dict:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DictDO dict = dictService.get(id);
		model.addAttribute("dict", dict);
	    return "sys/dict/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dict:add")
	public ResultVO save( DictDO dict){
		if(dictService.save(dict)>0){
			return ResultVO.ok();
		}
		return ResultVO.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dict:edit")
	public ResultVO update( DictDO dict){
		dictService.update(dict);
		return ResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:dict:remove")
	public ResultVO remove( Long id){
		if(dictService.remove(id)>0){
		return ResultVO.ok();
		}
		return ResultVO.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:dict:batchRemove")
	public ResultVO remove(@RequestParam("ids[]") Long[] ids){
		dictService.batchRemove(ids);
		return ResultVO.ok();
	}
	
}
