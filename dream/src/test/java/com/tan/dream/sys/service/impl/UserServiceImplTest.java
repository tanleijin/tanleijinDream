package com.tan.dream.sys.service.impl;

import com.tan.dream.sys.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @Author: tanleijin
 * @description ()
 * @Date:2018/5/12 10:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void get() throws Exception {
    }

    @Test
    public void list() throws Exception {

        Map map = new HashMap();
        map.put("offset",0);
        map.put("limit",10);
        map.put("page",1);

        userService.list(map);

    }

    @Test
    public void count() throws Exception {
    }

}