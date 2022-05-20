package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Singer;

import java.util.List;

public interface SingerService extends IService<Singer> {
    boolean addSinger(Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerPic(Singer singer);

    boolean deleteSinger(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);
}
