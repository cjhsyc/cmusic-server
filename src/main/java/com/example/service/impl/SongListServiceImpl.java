package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.SongListMapper;
import com.example.domain.SongList;
import com.example.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {
    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateById(songList) >0 ;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return songListMapper.deleteById(queryWrapper) >0 ;
    }

    @Override
    public List<SongList> allSongList()
    {
        return songListMapper.selectList(null);
    }

    @Override
    public List<SongList> likeTitle(String title)
    {

        return songListMapper.likeTitle(title);
    }

    @Override
    public List<SongList> likeStyle(String style)
    {

        return songListMapper.likeStyle(style);
    }

    @Override
    public boolean addSongList(SongList songList)
    {
        return songListMapper.insert(songList) > 0;
    }

    @Override
    public boolean updateSongListImg(SongList songList) {

        return songListMapper.updateById(songList) >0 ;
    }
}
