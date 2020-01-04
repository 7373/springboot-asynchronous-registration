package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mailUtils.SendQQMailUtil;
import com.google.common.collect.Maps;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.plugin.util.UIUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


@Service
public class UserService {

  public static Map<String,String> userId= Maps.newConcurrentMap();
    @Async("taskExecutor")
    public void doSendMail(User user) throws Exception {
        long start = System.currentTimeMillis();
        String uuid=UUID.randomUUID().toString().substring(0,5);
        SendQQMailUtil.sendQQmail("注册验证码",uuid,user.getEmail());
        userId.put(user.getUsername(),uuid);
    }
}
