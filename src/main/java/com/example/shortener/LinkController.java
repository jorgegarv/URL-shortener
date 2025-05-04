package com.example.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(
            @RequestBody Map<String, String> request,
            HttpServletRequest servletRequest) {  
    	
        String originalUrl = request.get("url");
        if (originalUrl == null || originalUrl.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "URL is required"));
        }

        String shortCode = UUID.randomUUID().toString().substring(0, 8);
        Link link = new Link(shortCode, originalUrl);
        linkRepository.save(link);

        String baseUrl = servletRequest.getRequestURL()
                            .toString()
                            .replace(servletRequest.getRequestURI(), "");

        Map<String, String> response = new HashMap<>();
        response.put("originalUrl", originalUrl);
        response.put("shortUrl", shortCode);
        response.put("fullShortUrl", baseUrl + "/" + shortCode);  // ← URL dinámica

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        return linkRepository.findById(shortUrl)
                .map(link -> {
                    link.incrementClickCount();
                    linkRepository.save(link);
                    return new RedirectView(link.getOriginalUrl());
                })
                .orElse(new RedirectView("/error"));
    }
}