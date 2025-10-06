package com.task.urlshortner.dto;


import java.time.LocalDateTime;

public class RecentClickDto {
    public String url;
    public String shortCode;
    public String ipAddress;
    public LocalDateTime clickedAt;
    public RecentClickDto(String url, String shortcode, String ipAddress, LocalDateTime clickedAt) {
        this.url = url;
        this.shortCode = shortcode;
        this.ipAddress = ipAddress;
        this.clickedAt = clickedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getClickedAt() {
        return clickedAt;
    }

    public void setClickedAt(LocalDateTime clickedAt) {
        this.clickedAt = clickedAt;
    }
}
