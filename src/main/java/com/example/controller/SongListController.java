package com.example.controller;


import com.example.common.Message;
import com.example.constant.Constants;
import com.example.domain.SongList;
import com.example.service.impl.SongListServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("songList")
public class SongListController {
    @Autowired
    private SongListServiceImpl songListService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**")
                    .addResourceLocations(Constants.SONGLIST_PIC_PATH);
        }
    }

    // 添加歌单
    @ResponseBody
    @PostMapping("add")
    public Message addSongList(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        String pic = "/img/songListPic/123.jpg";

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        songList.setPic(pic);

        boolean res = songListService.addSongList(songList);
        if (res) {
            return new Message("success","添加成功");
        } else {
            return new Message("error","添加失败");
        }
    }

    // 删除歌单
    @GetMapping("delete")
    public Message deleteSongList(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songListService.deleteSongList(Integer.parseInt(id));
        if (res) {
            return new Message("success","删除成功");
        } else {
            return new Message("error","删除失败");
        }
    }

    // 返回所有歌单
    @GetMapping
    public Message allSongList() {

        return new Message("success",null,songListService.allSongList());
    }

    // 返回标题包含文字的歌单 err
    @GetMapping("likeTitle/detail")
    public Message songListOfLikeTitle(HttpServletRequest req) {
        String title = req.getParameter("title").trim();

        return new Message("success",null,songListService.likeTitle('%' + title + '%'));
    }

    // 返回指定类型的歌单 err
    @GetMapping("style/detail")
    public Message songListOfStyle(HttpServletRequest req) {
        String style = req.getParameter("style").trim();

        return new Message("success",null,songListService.likeStyle('%' + style + '%'));
    }

    // 更新歌单信息
    @ResponseBody
    @PostMapping("update")
    public Message updateSongListMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean res = songListService.updateSongListMsg(songList);
        if (res) {
            return new Message("success","修改成功");
        } else {
            return new Message("error","修改失败");
        }
    }

    // 更新歌单图片
    @ResponseBody
    @PostMapping("img/update")
    public Message updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(imgPath);

            boolean res = songListService.updateSongListImg(songList);
            if (res) {
                return new Message("success","上传成功",imgPath);
            } else {
                return new Message("error","上传失败");
            }
        } catch (IOException e) {
            return new Message("fatal","上传失败" + e.getMessage());
        }
    }
}
