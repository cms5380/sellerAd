package com.charminseok.settlement.config;

import com.charminseok.settlement.domain.SettlementDomain;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JobConfig {
    @Bean
    public BatchProperties.Job inactiveSettlementJob(JobBuilderFactory jobBuilderFactory, Step inactiveJobStep) { //(1)
        return jobBuilderFactory.get("inactiveUserJob")
                .preventRestart() //(2)
                .start(inactiveJobStep) //(3)
                .build();
    }

    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("inactiveUserStep") //(1)
                .<SettlementDomain, SettlementDomain> chunk(10) //(2)
                .reader(inactiveUserReader()) //(3)
                .processor(inactiveUserProcessor())
                .writer(inactiveUserWriter())
                .build();
    }

    @Bean
    @StepScope //(1)
    public QueueItemReader<SettlementDomain> inactiveSettlementReader() {
        //(2)
//        List<SettlementDomain> oldUsers =
//                userRepository.findByUpdatedDateBeforeAndStatusEquals(
//                        LocalDateTime.now().minusYears(1),
//                        UserStatus.ACTIVE);

        return new QueueItemReader<>(oldUsers); //(3)
    }
}
