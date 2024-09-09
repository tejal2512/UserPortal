package com.example.UserManagement_portal.service;

import com.example.UserManagement_portal.dto.QuoteApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DashboardServiceImpl implements DashboardService{

    private String quoteApiURL="https://dummyjson.com/quotes/random";
    @Override
    public QuoteApiResponseDTO getQuote() {
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<QuoteApiResponseDTO> forEntity=restTemplate.getForEntity(quoteApiURL, QuoteApiResponseDTO.class);
        QuoteApiResponseDTO body=forEntity.getBody();
        return body;
    }
}
