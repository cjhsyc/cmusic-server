package com.example.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongMapper extends BaseMapper<Song> {
    @Select("    select *\n" +
            "    from song\n" +
            "    where name like #{name}")
    List<Song> songOfSingerName(String name);
}
