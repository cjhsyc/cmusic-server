package com.example;

import com.example.common.Message;
import com.example.dao.ConsumerMapper;
import com.example.domain.Consumer;
import com.example.service.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CmusicServerApplicationTests {
    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private ConsumerService consumerService;

    @Test
    void contextLoads() {
        Message message = new Message("success");
        System.out.println(message);
    }

    @Test
    void test1() {
        Consumer consumer = new Consumer();
        consumer.setBirth(new Date());
        consumer.setUsername("jing1");
        consumer.setPassword("123");
        consumer.setAvator("/img/avatorImages/user.jpg");
        consumer.setUpdateTime(new Date());
        consumer.setCreateTime(new Date());
        System.out.println(consumer);
        consumerService.save(consumer);
    }

    @Test
    void test2(){
        Consumer consumer = new Consumer();
        consumer.setId(26);
        consumer.setUsername("jing1");
        consumer.setPhoneNum(null);
        System.out.println(consumer);
        consumerService.updateById(consumer);
    }

    @Test
    void test3(){
        Consumer consumer = new Consumer();
        consumer.setId(26);
        consumer.setUsername("jing1");
        consumer.setBirth(new Date());
        consumer.setPhoneNum("12309874576");
        consumer.setPassword("1234");
        System.out.println(consumer);
        System.out.println(consumerMapper.update(consumer));
    }
}
