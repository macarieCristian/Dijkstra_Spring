package com.practice.dijkstra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class CountryModel {
    private String cca3;
    private List<Double> latlng;
    private Set<String> borders;
}
