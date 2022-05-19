package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.ListSong;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ListSongMapper extends BaseMapper<ListSong> {

    @Select("    select *\n" +
            "    from list_song\n" +
            "    where song_list_id = #{songListId, jdbcType=INTEGER}")
    List<ListSong> listSongOfSongId(Integer songListId);
}
