package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.SingerMapper;
import com.example.domain.Singer;
import com.example.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {
    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateById(singer) > 0;
    }

    @Override
    public boolean updateSingerPic(Singer singer) {

        return singerMapper.updateById(singer) > 0;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return singerMapper.delete(queryWrapper) >0;
    }

    @Override
    public List<Singer> allSinger()
    {
        return singerMapper.selectList(null);
    }

    @Override
    public boolean addSinger(Singer singer)  {

        return singerMapper.insert(singer) > 0;
    }

    @Override
    public List<Singer> singerOfName(String name)
    {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return singerMapper.selectList(queryWrapper);
    }

    @Override
    public List<Singer> singerOfSex(Integer sex)

    {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex",sex);
        return singerMapper.selectList(queryWrapper);
    }
}
