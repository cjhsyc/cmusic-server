package com.example.controller;

import com.example.common.Message;
import com.example.common.UpdatePwdData;
import com.example.constant.Constants;
import com.example.domain.Consumer;
import com.example.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("user")
public class ConsumerController {
    @Autowired
    private ConsumerServiceImpl consumerService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/avatorImages/**")
                    .addResourceLocations(Constants.AVATOR_IMAGES_PATH);
        }
    }

    //返回所有用户
    @GetMapping
    public Message allUser() {
        return new Message("success", null, consumerService.list());
    }

    //登录
    @PostMapping("login")
    public Message login(Consumer consumer) {
        List<Consumer> consumerList = consumerService.searchByNameAndPwd(consumer.getUsername(), consumer.getPassword());
        if (consumerList.size() > 0) {
            return new Message("success", "登录成功", consumerList);
        } else {
            return new Message("error", "用户名或密码错误");
        }
    }

    //注册
    @PostMapping
    public Message register(Consumer consumer) {
        if (consumerService.existUsername(consumer.getUsername())) {
            return new Message("warning", "用户名已注册");
        }
        if (consumer.getPhoneNum() != null && !consumer.getPhoneNum().equals("") && consumerService.existPhoneNum(consumer.getPhoneNum())) {
            return new Message("warning", "手机号已注册");
        }
        if (consumer.getEmail() != null && !consumer.getEmail().equals("") && consumerService.existEmail(consumer.getEmail())) {
            return new Message("warning", "邮箱已注册");
        }
        if (consumerService.addConsumer(consumer)) {
            return new Message("success", "注册成功");
        } else {
            return new Message("error", "注册失败");
        }
    }

    //注销
    @DeleteMapping("{id}")
    public Message logout(@PathVariable Integer id) {
        if (consumerService.removeById(id)) {
            return new Message("success", "注销成功");
        } else {
            return new Message("error", "注销失败");
        }
    }

    //返回用户信息
    @GetMapping("{id}")
    public Message getUserById(@PathVariable Integer id) {
        Consumer consumer = consumerService.getById(id);
        return new Message("success", null, consumer);
    }

    //更新用户信息
    @PutMapping
    public Message update(Consumer consumer) {
        if (consumerService.update(consumer)) {
            return new Message("success", "修改成功");
        } else {
            return new Message("error", "修改失败");
        }
    }

    //更新用户密码
    @PutMapping("password")
    public Message updatePwd(UpdatePwdData updatePwdData) {
        List<Consumer> consumerList = consumerService.searchByNameAndPwd(updatePwdData.getUsername(), updatePwdData.getOld_password());
        if (consumerList.size() == 0) {
            return new Message("error", "旧密码输入错误");
        }
        if (consumerService.updatePassword(updatePwdData)) {
            return new Message("success", "密码修改成功");
        } else {
            return new Message("error", "密码修改失败");
        }
    }

    //更新用户头像
    @PostMapping("avatar/{id}")
    public Message updateAvatar(@PathVariable Integer id, @RequestParam("file") MultipartFile avatarFile) {
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        String filePath = Constants.PROJECT_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(file.mkdirs());
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatorImages/" + fileName;
        try {
            avatarFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(imgPath);
            if (consumerService.updateById(consumer)) {
                return new Message("success", "上传成功", imgPath);
            } else {
                return new Message("error", "上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Message("fatal", e.getMessage());
        }
    }
}