package com.example.controller;


import com.example.common.Message;
import com.example.service.impl.ListSongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("listSong")
public class ListSongController {
    @Autowired
    private ListSongServiceImpl listSongService;

    // 返回歌单里指定歌单 ID 的歌曲
    @GetMapping("detail/{songListId}")
    public Message listSongOfSongId(@PathVariable Integer songListId) {
        return new Message("success", null, listSongService.listSongOfSongId(songListId));
    }
}
