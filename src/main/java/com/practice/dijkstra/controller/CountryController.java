package com.practice.dijkstra.controller;

import com.practice.dijkstra.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routing")
@RequiredArgsConstructor
@Validated
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<List<String>> getShortestDistance(@PathVariable("origin") @Length(min = 3, max = 3) String startCountryCode,
                                                            @PathVariable("destination") @Length(min = 3, max = 3) String destinationCountryCode) {
        return ResponseEntity.ok(countryService.computeShortestPath(startCountryCode, destinationCountryCode));
    }
}
