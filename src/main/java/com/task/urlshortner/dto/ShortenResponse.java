package com.task.urlshortner.dto;

import java.time.LocalDateTime;


public class ShortenResponse {
    public String shortcode;
    public String shorturl;
    public LocalDateTime timestamp;
    public ShortenResponse(){}

    public ShortenResponse(String shortcode, String shorturl, LocalDateTime timestamp) {
        this.shortcode = shortcode;
        this.shorturl = shorturl;
        this.timestamp = timestamp;
    }

    public String getShortcode() { return shortcode; }
    public String getShorturl() { return shorturl; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setShortcode(String shortcode) { this.shortcode = shortcode; }
    public void setShorturl(String shorturl) { this.shorturl = shorturl; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
