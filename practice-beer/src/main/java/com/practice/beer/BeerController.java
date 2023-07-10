package com.practice.beer;

import com.practice.beer.dto.BeerGetDto;
import com.practice.beer.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BeerController {

    @PostMapping("/give-me-beer")
    public MessageDto giveMeBeer(@RequestBody BeerGetDto dto){
        log.info(dto.toString());
        log.info("꺼어어억");
        MessageDto messageDto =  new MessageDto();
        messageDto.setMessage("꺼어어어어어억");
        return messageDto;
    }

    @PostMapping("/give-me-beer-204")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDto giveMeBeer204(@RequestBody BeerGetDto dto){
        log.info(dto.toString());
        log.info("꺼어어억204");
        MessageDto messageDto =  new MessageDto();
        messageDto.setMessage("꺼어어어어어억204");
        return messageDto;
    }
}
