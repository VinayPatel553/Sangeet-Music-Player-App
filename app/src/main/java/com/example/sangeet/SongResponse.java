package com.example.sangeet;

import java.util.List;

public class SongResponse {
    private List<Song> data;

    // Getter
    public List<Song> getData() { return data; }

    // Setter
    public void setData(List<Song> data) { this.data = data; }
}