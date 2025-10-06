package com.task.urlshortner.service;

import com.task.urlshortner.dto.RecentClickDto;
import com.task.urlshortner.dto.TopUrlsDto;
import com.task.urlshortner.entity.ClickAnalytics;
import com.task.urlshortner.entity.UrlMapping;
import com.task.urlshortner.exception.ErrorHandler;
import com.task.urlshortner.repository.ClickAnalyticsRepo;
import com.task.urlshortner.repository.UrlMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClickAnalyticsService {
    private final ClickAnalyticsRepo clickAnalyticsRepo;
    private final UrlMappingRepo urlMappingRepo;

    @Autowired
    public ClickAnalyticsService(ClickAnalyticsRepo clickAnalyticsRepo, UrlMappingRepo urlMappingRepo) {
        this.clickAnalyticsRepo = clickAnalyticsRepo;
        this.urlMappingRepo = urlMappingRepo;
    }

    public List<ClickAnalytics> getClicks(String shortcode){
        try{
            return clickAnalyticsRepo.findByShortCode(shortcode);
        }catch (Exception e){
            throw new ErrorHandler("Internal Error");
        }
    }
    public List<TopUrlsDto> getUrls(Long n){
        try{

        List<UrlMapping> res = urlMappingRepo.findAll();
        return res.stream()
                .map(url -> new TopUrlsDto(
                url.getShortcode(),
                url.getUrl(),
                url.getClickAnalytics().size()
        ))
                .sorted(Comparator.comparingLong(TopUrlsDto::getTotalClicks).reversed())
                .limit(n)
                .toList();
        }catch (Exception e){
            throw new ErrorHandler("Internal Error");
        }
    }

//    features using stream api
    public Map<LocalDate, Long> getDailyClickStats(String shortCode) {
        try{
        return clickAnalyticsRepo.findByShortCode(shortCode).stream()
                .collect(Collectors.groupingBy(
                        click -> click.getClickedAt().toLocalDate(),
                        Collectors.counting()
                ));
        }catch (Exception e){
            throw new ErrorHandler("Internal Error");
        }
    }
    public List<RecentClickDto> getRecentClicks() {
        try{

            LocalDateTime since = LocalDateTime.now().minusHours(24);

            return clickAnalyticsRepo.findAll().stream()
                .filter(c -> c.getClickedAt().isAfter(since))
                .sorted(Comparator.comparing(ClickAnalytics::getClickedAt).reversed())
                .map(c -> new RecentClickDto(
                        c.getUrlMapping().getUrl(),
                        c.getUrlMapping().getShortcode(),
                        c.getIpAddress(),
                        c.getClickedAt()
                ))
                .toList();
        }catch (Exception e){
            throw new ErrorHandler("Internal Error");
        }
    }
}
