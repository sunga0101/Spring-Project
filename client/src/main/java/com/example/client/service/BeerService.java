package com.example.client.service;

import com.example.client.client.BeerRestClient;
import com.example.client.dto.BeerGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeerService {
    private final BeerRestClient client;
    public BeerService(BeerRestClient client){
        this.client = client;
    }

    public void drinkBeer() {
        log.info("order beer");
        BeerGetDto result = client.getBeer();
        // TODO API를 활용해 맥주 정보 받아오기
        // 핵심은 맥주 정보
        // 맥주 정보를 받아오는 방법은 비즈니스 로직에서 벗어났다
        // 할수 있지 않을까?
        log.info("{}는 맛있다", result.getName());
    }
}