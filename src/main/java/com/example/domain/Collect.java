package com.example.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Collect {
    private Integer id;
    private Integer userId;
    private Byte type;
    private Integer songId;
    private Date createTime;
}
