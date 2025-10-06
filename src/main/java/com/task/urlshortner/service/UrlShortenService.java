package com.task.urlshortner.service;

import com.task.urlshortner.dto.ShortenResponse;
import com.task.urlshortner.entity.ClickAnalytics;
import com.task.urlshortner.entity.UrlMapping;
import com.task.urlshortner.exception.ErrorHandler;
import com.task.urlshortner.repository.ClickAnalyticsRepo;
import com.task.urlshortner.repository.UrlMappingRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenService {

    private final UrlMappingRepo urlMappingRepo;
    private final ClickAnalyticsRepo clickAnalyticsRepo;

    @Autowired
    public UrlShortenService(UrlMappingRepo urlMappingRepo, ClickAnalyticsRepo clickAnalyticsRepo){
        this.urlMappingRepo = urlMappingRepo;
        this.clickAnalyticsRepo = clickAnalyticsRepo;
    }
    public ShortenResponse generateShortUrl(String url)  { //url shorten service generates code and saves it into the db
        try{
            UrlMapping abc = urlMappingRepo.findByUrl(url);
            String provider = "http://localhost:8080/";
            if(abc != null) return new ShortenResponse(abc.getShortcode(), provider +abc.getShortcode(),abc.getCreatedAt());
            String code = generateCode();
            String shortUrl = provider +code;
            urlMappingRepo.save(new UrlMapping(code,url,LocalDateTime.now()));
            return new ShortenResponse(code,shortUrl,LocalDateTime.now());
        } catch (Exception e) {
            throw new ErrorHandler("Internal Error...");
        }
    }
    public String generateCode(){  // generates 6 characters code
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < 6 ; i++){
            String base = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            text.append(base.charAt(new Random().nextInt(base.length())));
        }
        return text.toString();
    }

    public ResponseEntity<?> RedirectToDestination(String code, HttpServletRequest req){
        try {
            UrlMapping res = urlMappingRepo.findByShortcode(code);
            if(res == null) throw new ErrorHandler("Invalid Code");
            Map<String,String> body = new HashMap<>();
            try {
                ClickAnalytics ca = new ClickAnalytics();
                ca.setShortCode(res.getShortcode());
                ca.setIpAddress(String.valueOf(req.getRemoteAddr()));
                ca.setClickedAt(LocalDateTime.now());
                ca.setUrlMapping(res);
                clickAnalyticsRepo.save(ca);
            }catch (ErrorHandler e){
                throw new ErrorHandler("Error Saving data");
            }
            body.put("Status",HttpStatus.FOUND.toString());
            body.put("RedirectUrl",res.getUrl());
//            return new ResponseEntity<>(body,HttpStatus.TEMPORARY_REDIRECT);
            return ResponseEntity.status(HttpStatus.FOUND).header("Location",res.getUrl()).build();
        }catch (ErrorHandler e){
            throw new ErrorHandler(e.getMessage());
        }catch (Exception ex){
            throw new ErrorHandler("Internal Error...");
        }
    }
}
