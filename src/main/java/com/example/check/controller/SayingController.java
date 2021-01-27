package com.example.check.controller;

import com.example.check.payload.request.SayingRequest;
import com.example.check.payload.response.SayingResponse;
import com.example.check.service.saying.SayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saying")
public class SayingController {

    private final SayingService sayingService;

    @PostMapping
    public void createSaying(@RequestBody SayingRequest sayingRequest) {
        sayingService.createSaying(sayingRequest.getContent());
    }

    @GetMapping
    public SayingResponse getSaying() {
        return sayingService.getSaying();
    }

}
