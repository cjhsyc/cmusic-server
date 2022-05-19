package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface    SongListMapper extends BaseMapper<SongList> {
    @Select("    select *\n" +
            "    from song_list\n" +
            "    where title like #{title}")
    List<SongList> likeTitle(String title);

    @Select("    select *\n" +
            "    from song_list\n" +
            "    where style like #{style,jdbcType=VARCHAR}")
    List<SongList> likeStyle(String style);
}
