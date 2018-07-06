package com.tan.dream.sys.service;

import java.io.PrintStream;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

import com.tan.dream.sys.domain.UserDO;
import com.tan.dream.sys.domain.UserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;


@Service
public interface SessionService {
	List<UserOnline> list();

	List<UserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
