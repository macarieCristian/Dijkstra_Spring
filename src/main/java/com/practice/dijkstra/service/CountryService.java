package com.practice.dijkstra.service;

import com.practice.dijkstra.exception.BadRequestException;
import com.practice.dijkstra.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<String> computeShortestPath(String startCountryCode, String destinationCountryCode) {
        Map<String, Map<String, Double>> countryCodesGraph = countryRepository.getCountryCodesGraph();
        if (!countryCodesGraph.containsKey(startCountryCode)) {
            throw new BadRequestException(MessageFormat.format("The code: {0} is not a valid country code.", startCountryCode));
        }

        if (!countryCodesGraph.containsKey(destinationCountryCode)) {
            throw new BadRequestException(MessageFormat.format("The code: {0} is not a valid country code.", destinationCountryCode));
        }

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(countryCodesGraph);
        algorithm.execute(startCountryCode); // Future improvement (result can be cached)
        List<String> shortestPath = algorithm.getPath(destinationCountryCode);

        if (shortestPath == null) {
            throw new BadRequestException(MessageFormat.format("No land crossing", destinationCountryCode));
        }

        return algorithm.getPath(destinationCountryCode);
    }
}
