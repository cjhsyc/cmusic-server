package com.example.service;

import com.example.domain.Rank;

public interface RankService {
    Integer getRank(Integer id);

    Integer getUserRank(Integer userId, Integer id);

    Boolean setRank(Rank rank);
}
