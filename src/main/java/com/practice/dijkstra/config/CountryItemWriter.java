package com.practice.dijkstra.config;

import com.practice.dijkstra.entity.Country;
import com.practice.dijkstra.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@RequiredArgsConstructor
public class CountryItemWriter implements ItemWriter<Country> {
    private final CountryRepository countryRepository;

    @Override
    public void write(List<? extends Country> list) throws Exception {
        countryRepository.addCountries(list);
    }
}
