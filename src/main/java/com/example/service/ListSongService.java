package com.example.service;


import com.example.domain.ListSong;

import java.util.List;

public interface ListSongService {
    List<ListSong> listSongOfSongId(Integer songListId);
}
