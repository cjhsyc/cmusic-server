package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ConsumerMapper extends BaseMapper<Consumer> {
    @Update("update consumer " +
                "set username = #{username}, " +
                    "password = #{password}, " +
                    "sex = #{sex}, " +
                    "phone_num = #{phoneNum}, " +
                    "email = #{email}, " +
                    "birth = #{birth}, " +
                    "introduction = #{introduction}, " +
                    "location = #{location} " +
                "where id = #{id}")
    Integer update(Consumer consumer);
}