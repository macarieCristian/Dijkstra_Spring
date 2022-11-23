package com.practice.dijkstra.repository;

import com.practice.dijkstra.entity.Country;
import lombok.Getter;
import org.apache.lucene.util.SloppyMath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CountryRepository {
    private static final Logger logger = LoggerFactory.getLogger(CountryRepository.class);

    @Getter
    private final Map<String, Map<String, Double>> countryCodesGraph = new HashMap<>();
    private final Map<String, Country> countriesMap = new HashMap<>();

    public void addCountries(List<? extends Country> countries) {
        countries.forEach(country -> countriesMap.put(country.getCode(), country));
    }

    public void initializeWeightedGraph() {
        for (Country country : countriesMap.values()) {
            countryCodesGraph.put(country.getCode(), new HashMap<>());

            for (String neighbour : country.getNeighbours()) {
                final Country neighbourCountry = countriesMap.get(neighbour);
                final Double distanceInMeters = SloppyMath.haversinMeters(country.getLatitude(), country.getLongitude(),
                        neighbourCountry.getLatitude(), neighbourCountry.getLongitude());

                countryCodesGraph.get(country.getCode()).put(neighbour, distanceInMeters);
            }
        }

        logger.info("Graph was initialized successfully!");
    }
}
