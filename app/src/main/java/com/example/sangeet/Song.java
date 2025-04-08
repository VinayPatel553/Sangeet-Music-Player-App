package com.example.sangeet;

public class Song {
    private String id;
    private String name;
    private Integer duration;
//    private List<DownloadUrl> downloadUrl;

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public Integer getDuration() { return duration; }
//    public List<DownloadUrl> getDownloadUrl() { return downloadUrl; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDuration(Integer duration) { this.duration = duration; }
//    public void setDownloadUrl(List<DownloadUrl> downloadUrl) { this.downloadUrl = downloadUrl; }
}
