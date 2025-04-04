package com.example.sangeet;

import java.util.List;

public class SongResponse {
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }
}

class Song {
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

