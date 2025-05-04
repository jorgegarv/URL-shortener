package com.example.shortener;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Link {
    @Id
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createdAt;
    private int clickCount;

    public Link() {
        this.createdAt = LocalDateTime.now();
    }

    public Link(String shortUrl, String originalUrl) {
        this();
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    // Getters y Setters
    public String getShortUrl() { return shortUrl; }
    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }
    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getClickCount() { return clickCount; }
    public void incrementClickCount() { this.clickCount++; }
}