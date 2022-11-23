package com.practice.dijkstra.config;

import com.practice.dijkstra.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@RequiredArgsConstructor
public class ImportCountriesJobListener implements JobExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(ImportCountriesJobListener.class);

    private final CountryRepository countryRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job start");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        countryRepository.initializeWeightedGraph();
    }
}
