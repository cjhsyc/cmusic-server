package com.example.controller;

import com.example.common.Message;
import com.example.constant.Constants;
import com.example.service.impl.SingerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("singer")
public class SingerController {
    @Autowired
    private SingerServiceImpl singerService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/singerPic/**")
                    .addResourceLocations(Constants.SINGER_PIC_PATH);
        }
    }

    // 返回所有歌手
    @GetMapping
    public Message allSinger() {
        return new Message("success", null, singerService.allSinger());
    }

    // 根据歌手名查找歌手
    @GetMapping("name/detail")
    public Message singerOfName(HttpServletRequest req) {
        String name = req.getParameter("name").trim();

        return new Message("success", null, singerService.singerOfName(name));
    }

    // 根据歌手性别查找歌手
    @GetMapping("sex/detail/{sex}")
    public Message singerOfSex(@PathVariable Integer sex) {
        return new Message("success", null, singerService.singerOfSex(sex));
    }
}
