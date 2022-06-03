package com.example.controller;

import com.example.common.Message;
import com.example.domain.Collect;
import com.example.service.impl.CollectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("collection")
public class CollectController {
    @Autowired
    CollectServiceImpl collectService;

    //收藏歌曲
    @PostMapping
    public Message addCollection(Collect collect) {
        System.out.println(collect);
        collect.setCreateTime(new Date());
        if (collectService.save(collect)) {
            return new Message("success", "收藏成功", true);
        } else {
            return new Message("error", "收藏失败");
        }
    }

    //取消收藏
    @DeleteMapping
    public Message deleteCollection(Collect collect) {
        System.out.println(collect + "___________delete");
        if (collectService.deleteCollection(collect)) {
            return new Message("success", "取消收藏", false);
        } else {
            return new Message("error", "取消收藏失败");
        }
    }

    @PostMapping("delete")
    public Message deleteTest(Collect collect){
        System.out.println(collect + "__________test");
        if (collectService.deleteCollection(collect)) {
            return new Message("success", "取消收藏", false);
        } else {
            return new Message("error", "取消收藏失败");
        }
    }

    //是否收藏歌曲
    @PostMapping("status")
    public Message getStatus(Collect collect) {
        if (collectService.isCollected(collect)) {
            return new Message("success", "已收藏", true);
        } else {
            return new Message("success", "未收藏", false);
        }
    }

    //返回指定用户的收藏列表
    @GetMapping("{id}")
    public Message userCollections(@PathVariable Integer id) {
        return new Message("success", null, collectService.getCollections(id));
    }
}