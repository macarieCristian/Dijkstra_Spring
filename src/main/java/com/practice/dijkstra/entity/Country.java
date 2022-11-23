package com.practice.dijkstra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Country {
    private String code;
    private Double latitude;
    private Double longitude;
    private Set<String> neighbours;
}
