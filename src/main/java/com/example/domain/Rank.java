package com.example.domain;

import lombok.Data;

@Data
public class Rank {
    private Long id;
    private Integer songListId;
    private Integer consumerId;
    private Integer score;
}
