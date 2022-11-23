package com.practice.dijkstra.config;

import com.practice.dijkstra.entity.Country;
import com.practice.dijkstra.model.CountryModel;
import com.practice.dijkstra.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CountryRepository countryRepository;

    @Bean
    public JsonItemReader<CountryModel> countryItemReader() {
        return new JsonItemReaderBuilder<CountryModel>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(CountryModel.class))
                .resource(new ClassPathResource("countries.json"))
                .name("jsonItemReader")
                .build();
    }

    @Bean
    public CountryItemProcessor countryItemProcessor() {
        return new CountryItemProcessor();
    }

    @Bean
    public CountryItemWriter countryItemWriter() {
        return new CountryItemWriter(countryRepository);
    }

    @Bean
    public Step importCountriesStep() {
        return stepBuilderFactory.get("importCountriesStep").<CountryModel, Country>chunk(30)
                .reader(countryItemReader())
                .processor(countryItemProcessor())
                .writer(countryItemWriter())
                .build();
    }

    @Bean
    public JobExecutionListener importCountriesJobListener() {
        return new ImportCountriesJobListener(countryRepository);
    }

    @Bean
    public Job runImportCountriesJob() {
        return jobBuilderFactory.get("importCountriesJob")
                .flow(importCountriesStep())
                .end()
                .listener(importCountriesJobListener())
                .build();
    }


}
