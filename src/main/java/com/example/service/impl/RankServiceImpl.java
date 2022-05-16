package com.example.service.impl;

import com.example.dao.RankMapper;
import com.example.domain.Rank;
import com.example.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    RankMapper rankMapper;

    @Override
    public Integer getRank(Integer id) {
        Integer count = rankMapper.getCount(id);
        return count == 0 ? 0 : rankMapper.getSumScore(id) / count;
    }

    @Override
    public Integer getUserRank(Integer userId, Integer id) {
        return rankMapper.getUserRank(userId, id);
    }

    @Override
    public Boolean setRank(Rank rank) {
        return rankMapper.setRank(rank) > 0;
    }
}
