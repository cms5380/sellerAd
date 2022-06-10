package com.charminseok.settlement.job;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;

public class JobBuilderFactory {
    private JobRepostiroy jobrepository;

    public JobBuilderFactory(JobRepository jobRepository){
        this.jobrepository = jobrepository;
    }

    public JobBuilder get(String name){
        JobBuilder builder = new JobBuilder(name).repository(jobrepository);
        return builder;
    }

}
