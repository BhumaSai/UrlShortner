package com.task.urlshortner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "click_analytics"
)
public class ClickAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "short_code",unique = true,nullable = false,length = 10)
    private String shortCode;
    private LocalDateTime clickedAt;
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(nullable = false)
    private UrlMapping urlMapping;

    public void setUrlMapping(UrlMapping urlMapping) {
        this.urlMapping = urlMapping;
    }
    public UrlMapping getUrlMapping() {
        return urlMapping;
    }
    //    Constructors
    public ClickAnalytics(){}
    public ClickAnalytics(String shortCode, LocalDateTime clickedAt, String ipAddress) {
        this.shortCode = shortCode;
        this.clickedAt = clickedAt;
        this.ipAddress = ipAddress;
    }
//    Getters and Setters
    public String getShortCode() { return shortCode; }
    public String getIpAddress() { return ipAddress; }
    public LocalDateTime getClickedAt() { return clickedAt; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }
    public void setClickedAt(LocalDateTime clickedAt) { this.clickedAt = clickedAt; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
