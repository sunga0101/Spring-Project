package com.example.client;

import com.example.client.MessageDto;
import com.example.client.dto.BeerGetDto;
import com.example.client.dto.BeerPostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BeerRestService {
    // getForObject -> 다른 거 필요 없고, 응답 body만 있으면 될 경우에 사용
    public void getBeerObject(){
        // RestTemplate : Spring에서 제공하는 기본 HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // RestTemplate으로 GET 요청
//        String responseBody = restTemplate.getForObject(url, String.class); // 자바 객체를 바꿈 -> 역직렬화
//        log.info(responseBody);

        BeerGetDto responseBody = restTemplate.getForObject(url, BeerGetDto.class);
        log.info(responseBody.toString());
    }

    // getForEntity -> HTTP 응답 전체 확인
    public void getBeerEntity(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://random-data-api.com/api/v2/beers";

        // RestController
        ResponseEntity<BeerGetDto> response =
                restTemplate.getForEntity(url, BeerGetDto.class);
        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());
    }

    public void postBeerObject(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer";

        BeerPostDto dto = new BeerPostDto();
        MessageDto responseBody = restTemplate.postForObject(
                url, // 요청 URL
                dto, // request Body
                MessageDto.class // 응답 해석 타입
        );
        log.info(responseBody.toString());
    }
    public void postBeerEntity(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/give-me-beer-204";
        BeerPostDto dto = new BeerPostDto();
        // RestController
        ResponseEntity<Void> response = restTemplate.postForEntity(
                url,
                dto,
                Void.class  // void 의 클래스 (객체화 불가)
        );
        log.info(response.getStatusCode().toString());

    }

}
