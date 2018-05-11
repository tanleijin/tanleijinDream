package com.tan.dream.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * （描述）
 *
 * @Author tanleijin
 * @date Create in 22:23 2018/5/6
 */
@Controller
public class testController {

    @GetMapping("/test")
    public String test(){

        System.out.print("111111111111111");

        return "test";
    }

}
