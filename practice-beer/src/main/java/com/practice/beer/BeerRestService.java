package com.practice.beer;

import com.practice.beer.dto.BeerGetDto;
import com.practice.beer.dto.BeerPostDto;
import com.practice.beer.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BeerRestService {
    public void getBeerObject() {  // getForObject -> 다른거 필요 없고, 응답 Body 만 있으면 될때
        RestTemplate restTemplate = new RestTemplate(); // spring이 제공하는 기본 HTTP client
        String url = "https://random-data-api.com/api/v2/beers";
        // String
        String responseBody = restTemplate.getForObject(url, String.class); // url의 GET 응답을 String 형태로 해석해서 반환
        log.info(responseBody);

        // BeerGetDto
        BeerGetDto beerResponseBody = restTemplate.getForObject(url, BeerGetDto.class);
        log.info(beerResponseBody.toString());
    }

    // 내가 받고 싶은 응답
    // STATUS LINE
    // RESPONSE HEADER
    // RESPONSE BODY 이라면? -> getForObject 대신 getForEntity

    public void getBeerEntity(){
        String url = "https://random-data-api.com/api/v2/beers";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BeerGetDto> response = restTemplate.getForEntity(url, BeerGetDto.class);
        log.info(response.getStatusCode().toString());
        log.info(response.getHeaders().toString());
        log.info(response.getBody().toString());
    }

    // postForObject
    public void postBeerObject(){
        String url = "http://localhost:9090/give-me-beer";
        RestTemplate restTemplate = new RestTemplate();
        BeerPostDto dto = new BeerPostDto();
        dto.setName("Stella Artois");
        dto.setCc(2000L);
        dto.setAlcohol(5.0);
        // 문자열로 보낼 때
        String responseBodyString = restTemplate.postForObject(
                url,  // 요청 URL
                dto,  // request Body
                String.class  // 응답 해석 타입
        );
        log.info("!!!!!"+responseBodyString);


        // post 요청을 보낼 때 requestBody를 같이 전달
        MessageDto responseBody = restTemplate.postForObject(
                url,  // 요청 URL
                dto,  // request Body
                MessageDto.class  // 응답 해석 타입
        );
        log.info("!!!!!"+responseBody.toString());
    }

    // postForObject 응답 body 없을 때
    public void postBeerEntity204(){
        String url = "http://localhost:9090/give-me-beer-204";
        RestTemplate restTemplate = new RestTemplate();
        BeerPostDto dto = new BeerPostDto();
        dto.setName("Stella Artois"); // request 임의 생성
        dto.setCc(2000L);
        dto.setAlcohol(5.0);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, dto, Void.class);
        log.info(response.getStatusCode().toString());
    }

}
