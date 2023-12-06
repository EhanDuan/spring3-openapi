package com.demo.spring3openapi.controller;

import com.demo.spring3openapi.constant.HeaderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestHeader(HeaderConstant.HEADER_KEY) String value) {
        return ResponseEntity.ok(value);
    }
}
