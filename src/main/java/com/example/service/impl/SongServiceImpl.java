package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.SongMapper;
import com.example.domain.Song;
import com.example.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> allSong() {
        return songMapper.selectList(null);
    }

    @Override
    public boolean addSong(Song song) {
        return songMapper.insert(song) > 0;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateById(song) > 0;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateById(song) > 0;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateById(song) > 0;
    }

    @Override
    public boolean deleteSong(Integer id) {

        return songMapper.deleteById(id) > 0;
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id", singerId);
        return songMapper.selectList(queryWrapper);
    }

    @Override
    public List<Song> songOfId(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return songMapper.selectList(queryWrapper);
    }

    @Override
    public List<Song> songOfSingerName(String name) {
        //  QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        //  queryWrapper.eq("name",name);
        return songMapper.songOfSingerName(name);
    }
}
