package com.practice.dijkstra.config;

import com.practice.dijkstra.entity.Country;
import com.practice.dijkstra.model.CountryModel;
import org.springframework.batch.item.ItemProcessor;

public class CountryItemProcessor implements ItemProcessor<CountryModel, Country> {

    @Override
    public Country process(CountryModel countryModel) throws Exception {
        return Country.builder()
                .code(countryModel.getCca3())
                .latitude(countryModel.getLatlng().get(0))
                .longitude(countryModel.getLatlng().get(1))
                .neighbours(countryModel.getBorders())
                .build();
    }
}
