package com.example.controller;

import com.example.common.Message;
import com.example.constant.Constants;
import com.example.domain.Song;
import com.example.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("song")
public class SongController {
    @Autowired
    private SongServiceImpl songService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**")
                    .addResourceLocations(Constants.SONG_PIC_PATH);
            registry.addResourceHandler("/song/**")
                    .addResourceLocations(Constants.SONG_PATH);
        }
    }

    // 添加歌曲
    @ResponseBody
    @PostMapping("add")
    public Message addSong(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = req.getParameter("lyric").trim();

        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean res = songService.addSong(song);
            if (res) {
                return new Message("success", "上传成功", storeUrlPath);
            } else {
                return new Message("error", "上传失败");
            }
        } catch (IOException e) {
            return new Message("fatal", "上传失败" + e.getMessage());
        }
    }

    // 删除歌曲
    @GetMapping("delete")
    public Object deleteSong(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songService.deleteSong(Integer.parseInt(id));
        if (res) {
            return new Message("success", "删除成功");
        } else {
            return new Message("error", "删除失败");
        }
    }

    // 返回所有歌曲
    @GetMapping
    public Object allSong() {
        return new Message("success", null, songService.allSong());
    }

    // 返回指定歌曲ID的歌曲
    @GetMapping("detail")
    public Object songOfId(HttpServletRequest req) {
        String id = req.getParameter("id");

        return new Message("success", null, songService.songOfId(Integer.parseInt(id)));
    }

    // 返回指定歌手ID的歌曲
    @GetMapping("singer/detail")
    public Object songOfSingerId(HttpServletRequest req) {
        String singerId = req.getParameter("singerId");

        return new Message("success", null, songService.songOfSingerId(Integer.parseInt(singerId)));
    }

    // 返回指定歌手名的歌曲
    @GetMapping("singerName/detail")
    public Object songOfSingerName(HttpServletRequest req) {
        String name = req.getParameter("name");

        return new Message("success", null, songService.songOfSingerName('%' + name + '%'));
    }


}
