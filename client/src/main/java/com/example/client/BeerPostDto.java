package com.example.client;

import lombok.Data;

@Data
public class BeerPostDto {
    private String name;
    private Long cc;
    private Double alcohol;
}
