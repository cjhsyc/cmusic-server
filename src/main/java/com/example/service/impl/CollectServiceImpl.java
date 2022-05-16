package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CollectMapper;
import com.example.domain.Collect;
import com.example.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    CollectMapper collectMapper;
    @Override
    public Boolean deleteCollection(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("song_id",collect.getSongId());
        return collectMapper.delete(queryWrapper) > 0;
    }

    @Override
    public Boolean isCollected(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("song_id",collect.getSongId());
        return collectMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public List<Collect> getCollections(Integer id) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        return collectMapper.selectList(queryWrapper);
    }
}
