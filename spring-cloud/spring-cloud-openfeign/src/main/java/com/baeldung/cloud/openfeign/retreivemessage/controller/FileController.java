package com.baeldung.cloud.openfeign.retreivemessage.controller;

import com.baeldung.cloud.openfeign.retreivemessage.service.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    
    @Autowired
    private FetchService service;
    
    @PostMapping(value = "/fetch")
    public Boolean fetchDetails(@RequestParam String id ) {
        return service.getById(id);
    }
}