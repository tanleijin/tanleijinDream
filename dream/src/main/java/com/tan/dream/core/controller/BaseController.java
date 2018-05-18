package com.tan.dream.core.controller;

import com.tan.dream.common.shiro.utils.ShiroUtils;
import com.tan.dream.core.shiro.utils.ShiroUtils;
import com.tan.dream.sys.domain.UserDO;
import org.springframework.stereotype.Controller;


@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}