package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Rank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RankMapper {
    @Select("select count(*) from rank_list where songListId = #{id}")
    Integer getCount(Integer id);

    //coalesce:遇到null则返回括号中的下一个结果
    @Select("select coalesce(sum(score),0) from rank_list where songListId = #{id}")
    Integer getSumScore(Integer id);

    @Select("select score from rank_list where consumerId = #{userId} and songListId = #{id}")
    Integer getUserRank(@Param("userId") Integer userId, @Param("id") Integer id);

    @Insert("insert into rank_list(songListId,consumerId,score) values(#{songListId},#{consumerId},#{score})")
    Integer setRank(Rank rank);
}
