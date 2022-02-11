package com.baeldung.cloud.openfeign.retreivemessage.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.springframework.web.multipart.MultipartFile;

public interface FetchResource {
    
    @RequestLine("POST /fetch")
    @Headers("Content-Type: application/json")
    Response getDetailsById(@Param("id") String id);
    
}