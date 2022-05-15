package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.UpdatePwdData;
import com.example.dao.ConsumerMapper;
import com.example.domain.Consumer;
import com.example.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List<Consumer> searchByNameAndPwd(String name, String pwd) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", name);
        queryWrapper.eq("password", pwd);
        return consumerMapper.selectList(queryWrapper);
    }

    @Override
    public Boolean existUsername(String name) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", name);
        return consumerMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public Boolean existEmail(String email) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return consumerMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public Boolean existPhoneNum(String phoneNum) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_num", phoneNum);
        return consumerMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public Boolean addConsumer(Consumer consumer) {
        consumer.setAvator("/img/avatorImages/user.jpg");
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        if (consumer.getEmail().equals("")) {
            consumer.setEmail(null);
        }
        if (consumer.getPhoneNum().equals("")) {
            consumer.setPhoneNum(null);
        }
        return consumerMapper.insert(consumer) > 0;
    }

    @Override
    public Boolean update(Consumer consumer) {
        if (consumer.getEmail().equals("")) {
            consumer.setEmail(null);
        }
        if (consumer.getPhoneNum().equals("")) {
            consumer.setPhoneNum(null);
        }
        return consumerMapper.update(consumer) > 0;
    }

    @Override
    public Boolean updatePassword(UpdatePwdData updatePwdData) {
        Consumer consumer = new Consumer();
        consumer.setId(updatePwdData.getId());
        consumer.setPassword(updatePwdData.getPassword());
        return consumerMapper.updateById(consumer) > 0;
    }
}
