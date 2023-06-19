package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class HeaderController {
    // 인증정보를 전달하고 싶을 때 많이 사용함

    /* `header-one`로 들어온 HTTP 요청에 대하여
    *  헤더 중 `x-likelion` 이라는 헤더의 값을 인자로 받고 싶을 때*/
    @PostMapping("/header-one")
    public String getHeader(@RequestHeader("x-likelion") String header){
        log.info("POST /header-one header: "+header);
        return "index";
    }

    // `header-optional`으로 들어온 HTTP 요청에 대해서
    // 헤더 중 x-likelion이 있으면 할당, 없으면 null로 받고싶음
    @PostMapping("/header-optional")
    public String getHeaderOptional(@RequestHeader (value="x-likelion",required = false ) String header)
    {
        log.info("POST /header-optional header: "+header);
        return "index";
    }

    @PostMapping("/header-type")
    public String getHeaderInteger(@RequestHeader (value="x-likelion-int") Integer header){
        log.info("POST /header-type header: "+ header);
        return "index";
    }

    @PostMapping("/header-all")
    public String getHeaderAll(@RequestHeader // 모든 헤더를 다 가져올 때
                                   Map<String, List<String>> headerMap){ // <- 맵에 요청에 포함된 모든 헤더를 담아줌

//                                   HttpHeaders headerMap){
        log.info("POST /header-all");
//        for (Map.Entry<String, String> entry : headerMap.entrySet()){
        for (Map.Entry<String, List<String>> entry : headerMap.entrySet()){
            log.info(String.format("%s, %s", entry.getKey(), entry.getValue()));
        }
//        log.info("POST /header-all"+ headerMap);
        return "index";
    }
}
