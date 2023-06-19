package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;


@Slf4j
@Controller
public class MappingController {
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public String getPath(){
        log.info("GET /path");
        return "index";
    }

    @RequestMapping(value = "/path", method = RequestMethod.POST)
    public String postPat(){
        log.info("POST /path");
        return "index";
    }

    // 메소드 하나로 두가지 요청을 할 때
    @RequestMapping(value = "/path", method = { RequestMethod.PUT, RequestMethod.DELETE})
    public String putOrDeletePath(){
        log.info("PUT or DELETE /path");
        return "index";
    }

    @RequestMapping(value = "/path", method = RequestMethod.POST,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public String consumes(){
        log.info("POST /path Content-Type: application/json");
        return "index";
    }

    /* 특정된 상황에 사용하고 싶을 때
    *  헤더에 x-likelion이라는 값이 hello로 지정되어 있을 때 메소드를 실행하자 */
    @RequestMapping(value = "/path", method = RequestMethod.POST,
            headers = "x-likelion=hello")
    public String headerWith(){
        log.info("POST /path with x-likelion=hello");
        return "index";
    }

    /* `/path`로 오는 POST 요청이면서
    *  Query Parameter 로 likeliondl hello일 때*/
//  @RequestMapping(value = "/path", method = RequestMethod.POST, params = "likelion=hello") 동일함
    @PostMapping(value ="/path", params = "likelion=hello")
    public String params(){
        log.info("POST /path with parameter likelion");
        return "index";
    }

}
