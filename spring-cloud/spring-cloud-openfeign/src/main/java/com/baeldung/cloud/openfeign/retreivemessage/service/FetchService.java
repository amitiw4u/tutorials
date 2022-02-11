package com.baeldung.cloud.openfeign.retreivemessage.service;

import feign.Feign;
import feign.Response;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchService {
    private static final String HTTP_FETCH_URL = "http://localhost:8080";
    
    @Autowired
    private FetchClient client;
    
    public boolean getById(String id) {
        FetchResource fileUploadResource = Feign.builder().encoder(new SpringFormEncoder())
                .target(FetchResource.class, HTTP_FETCH_URL);
        Response response = fileUploadResource.getDetailsById(id);
        return response.status() == 200;
    }

}