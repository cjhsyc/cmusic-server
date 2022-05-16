package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Collect;

import java.util.List;

public interface CollectService extends IService<Collect> {
    Boolean deleteCollection(Collect collect);

    Boolean isCollected(Collect collect);

    List<Collect> getCollections(Integer id);
}
