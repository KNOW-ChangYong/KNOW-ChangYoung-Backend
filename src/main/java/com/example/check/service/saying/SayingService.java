package com.example.check.service.saying;

import com.example.check.payload.response.SayingResponse;

public interface SayingService {

    void createSaying(String content);

    SayingResponse getSaying();

}
