package com.task.urlshortner.repository;

import com.task.urlshortner.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepo extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByUrl(String url);
    UrlMapping findByShortcode(String shortcode);
}
