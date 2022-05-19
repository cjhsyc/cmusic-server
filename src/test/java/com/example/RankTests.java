package com.example;

import com.example.dao.RankMapper;
import com.example.dao.SongMapper;
import com.example.domain.Rank;
import com.example.service.impl.SongServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RankTests {
    @Autowired
    RankMapper rankMapper;

    @Autowired
    SongMapper songMapper;

    @Autowired
    SongServiceImpl songServiceImpl;

    @Test
    void test6(){
        System.out.println(songServiceImpl.allSong());
    }

    @Test
    void tese5(){
        System.out.println(songMapper.selectList(null));
    }

    @Test
    void test1(){
        System.out.println(rankMapper.getCount(1));
        System.out.println(rankMapper.getCount(123));
    }

    @Test
    void test2(){
        System.out.println(rankMapper.getSumScore(1));
        System.out.println(rankMapper.getSumScore(2));
        System.out.println(rankMapper.getSumScore(222));
    }

    @Test
    void test3(){
        System.out.println(rankMapper.getUserRank(1,1));
        System.out.println(rankMapper.getUserRank(1,9));
        System.out.println(rankMapper.getUserRank(1,10));
    }

    @Test
    void test4(){
        Rank rank = new Rank();
        rank.setConsumerId(29);
        rank.setSongListId(4);
        rank.setScore(8);
        System.out.println(rankMapper.setRank(rank));
    }
}
