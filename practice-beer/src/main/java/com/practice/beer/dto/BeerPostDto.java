package com.practice.beer.dto;

import lombok.Data;

@Data
public class BeerPostDto {
    private String name;
    private Long cc;
    private Double alcohol;
}