package com.task.urlshortner.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "url_mapping")
public class UrlMapping {

//    columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "short_code" ,unique = true,nullable = false,length = 10)
    private String shortcode;
    @Column(name="original_url",unique = true,nullable = false,columnDefinition = "TEXT")
    private String url;
    @Column(name = "created_at",unique = true)
    private LocalDateTime createdAt;

//    mapping to click analytics
    @OneToMany(mappedBy = "urlMapping", cascade = CascadeType.ALL)
    private List<ClickAnalytics> clickAnalytics;
//     constructors
    public UrlMapping(){}

    public UrlMapping(String shortcode, String url, LocalDateTime createdAt) {
        this.shortcode = shortcode;
        this.url = url;
        this.createdAt = createdAt;
    }

    public List<ClickAnalytics> getClickAnalytics() {
        return clickAnalytics;
    }

    public void setClickAnalytics(List<ClickAnalytics> clickAnalytics) {
        this.clickAnalytics = clickAnalytics;
    }

    //    getters and setters
    public String getShortcode() { return shortcode; }
    public String getUrl() { return url; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setShortcode(String shortcode) { this.shortcode = shortcode; }
    public void setUrl(String url) { this.url = url; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

}
