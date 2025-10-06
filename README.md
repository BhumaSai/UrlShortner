# Url Shortener

A simple Spring Boot application which generates shorten url

## Endpoints
    - Post 
        - /api/shorten?url=https://test.com/ 
    - GET 
        - /api/{shortcode}
        - /api/analytics/{shortcode}
        - /api/analytics/top
        - /api/analytics/dailystats/{shortcode}
        - /api/analytics/recentclicks

