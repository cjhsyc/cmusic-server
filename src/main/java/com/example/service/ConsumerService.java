package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Consumer;

import java.util.List;

public interface ConsumerService extends IService<Consumer> {
    List<Consumer> searchByNameAndPwd(String name, String pwd);

    Boolean existUsername(String name);

    Boolean existEmail(String email);

    Boolean existPhoneNum(String phoneNum);

    Boolean addConsumer(Consumer consumer);
}
