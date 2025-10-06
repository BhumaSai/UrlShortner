package com.task.urlshortner.dto;

public class TopUrlsDto {
    public String shortcode;
    public String url;
    public long totalClicks;

    public TopUrlsDto(String shortcode, String url, long totalclicks) {
        this.shortcode = shortcode;
        this.url = url;
        this.totalClicks = totalclicks;
    }

    public long getTotalClicks() {
        return totalClicks;
    }
    public String getShortcode(){
        return shortcode;
    }
    public String getUrl(){
        return url;
    }
}
