package com.example.controller;

import com.example.common.Message;
import com.example.domain.Consumer;
import com.example.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class ConsumerController {
    @Autowired
    private ConsumerServiceImpl consumerService;

    //返回所有用户
    @GetMapping
    public Message allUser() {
        return new Message("success", null, consumerService.list());
    }

    //登录
    @PostMapping("/login")
    public Message login(Consumer consumer) {
        List<Consumer> result = consumerService.searchByNameAndPwd(consumer.getUsername(), consumer.getPassword());
        if (result.size() > 0) {
            return new Message("success", "登录成功", result);
        } else {
            return new Message("error", "用户名或密码错误");
        }
    }

    //注册
    @PostMapping
    public Message register(Consumer consumer) {
        if (consumerService.existUsername(consumer.getUsername())) {
            return new Message("warning", "用户名已注册");
        }
        if (!consumer.getPhoneNum().equals("") && consumerService.existPhoneNum(consumer.getPhoneNum())) {
            return new Message("warning", "手机号已注册");
        }
        if (!consumer.getEmail().equals("") && consumerService.existEmail(consumer.getEmail())) {
            return new Message("warning", "邮箱已注册");
        }
        if (consumerService.addConsumer(consumer)) {
            return new Message("success", "注册成功");
        } else {
            return new Message("error", "注册失败");
        }
    }
}