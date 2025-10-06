package com.task.urlshortner.repository;

import com.task.urlshortner.entity.ClickAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClickAnalyticsRepo extends JpaRepository<ClickAnalytics,String> {
    List<ClickAnalytics> findByShortCode(String shortCode);
}
