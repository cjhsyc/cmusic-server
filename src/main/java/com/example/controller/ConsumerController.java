package com.example.controller;

import com.example.common.Message;
import com.example.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class ConsumerController {
    @Autowired
    private ConsumerServiceImpl consumerService;

    @GetMapping
    public Message allUser() {
        return new Message(200, consumerService.list());
    }
}