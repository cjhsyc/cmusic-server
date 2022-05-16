package com.example.controller;

import com.example.common.Message;
import com.example.domain.Rank;
import com.example.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rank")
public class RankController {
    @Autowired
    RankServiceImpl rankService;

    //获取指定歌单的评分
    @GetMapping("{id}")
    public Message getRank(@PathVariable Integer id) {
        return new Message("success", null, rankService.getRank(id));
    }

    //获取指定用户的评分
    @GetMapping("{userId}/{id}")
    public Message getUserRank(@PathVariable Integer userId, @PathVariable Integer id) {
        Integer rank = rankService.getUserRank(userId, id);
        if (rank != null) {
            return new Message("success", null, rank);
        } else {
            return new Message("error", "还未评分");
        }
    }

    //提交评分
    @PostMapping()
    public Message setRank(Rank rank) {
        if (rankService.setRank(rank)) {
            return new Message("success", "评价成功");
        } else {
            return new Message("error", "评价失败");
        }
    }
}
