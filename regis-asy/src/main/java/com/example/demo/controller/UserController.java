package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    public  Callable<String> regist(User user){

        Callable<String> result = () -> {
            TimeUnit.SECONDS.sleep(1);
            userService.doSendMail(user);
            return "success";
        };
        return result;
    }
    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    public String login(User user){
        if(user.getUuid().equals(UserService.userId.get(user.getUsername()))){
            return "success";
        }
        return "false";
    }
}
