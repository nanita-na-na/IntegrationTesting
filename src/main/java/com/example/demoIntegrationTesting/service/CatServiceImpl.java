package com.example.demoIntegrationTesting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatServiceImpl implements CatService {

    @Value("https://catfact.ninja/fact")
    private String url;


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getCatFact(String cat_fact) {
        CatResponse response = restTemplate.getForObject(url, CatResponse.class);

        if (response == null) {
            return null;
        }
        return response.getFact();
    }
}
