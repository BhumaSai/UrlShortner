package com.task.urlshortner.controller;


import com.task.urlshortner.dto.RecentClickDto;
import com.task.urlshortner.dto.ShortenResponse;
import com.task.urlshortner.dto.TopUrlsDto;
import com.task.urlshortner.entity.ClickAnalytics;
import com.task.urlshortner.service.ClickAnalyticsService;
import com.task.urlshortner.service.UrlShortenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class ApiHandler {
    @Autowired
    private UrlShortenService urlShortenService;
    @Autowired
    private ClickAnalyticsService clickAnalyticsService;
    @PostMapping("/api/shorten")
    public ResponseEntity<?> urlShortner(@Param("url") String url){
    // validating the url starts with https or http
        if(url.startsWith("http://") | url.startsWith("https://")) {
            ShortenResponse res = urlShortenService.generateShortUrl(url);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        return new ResponseEntity<>("invalid url",HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping("/{shortcode}")
    public ResponseEntity<?> redirectDestination(@PathVariable("shortcode") String shortcode, HttpServletRequest req){
        return urlShortenService.RedirectToDestination(shortcode,req);
    }

    @GetMapping("/api/analytics/{shortcode}")
    public List<ClickAnalytics> analyseData(@PathVariable String shortcode){
        return clickAnalyticsService.getClicks(shortcode);
    }

    @GetMapping("/api/analytics/top")
    public  ResponseEntity<List<TopUrlsDto>> TotalUrls(@RequestParam(value = "limit",required = false,defaultValue = "5") long n){
        List<TopUrlsDto> res = clickAnalyticsService.getUrls(n);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("/api/analytics/dailystats/{shortcode}")
    public Map<LocalDate, Long> dailyClickStats(@PathVariable String shortcode) {
        return clickAnalyticsService.getDailyClickStats(shortcode);
    }
    @GetMapping("/api/analytics/recentclicks")
    public List<RecentClickDto> recentClicks(){
        return clickAnalyticsService.getRecentClicks();
    }
    @RequestMapping("/**")
    public String invalid(){
        return  "Invalid url...";
    }
}
