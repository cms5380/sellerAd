package com.charminseok.settlement.config;

import com.charminseok.settlement.domain.SettlementDomain;
import com.charminseok.settlement.dto.ResponseAdvertisement;
import com.charminseok.settlement.tasklet.SettlementTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory; // Job 빌더 생성용
    private final StepBuilderFactory stepBuilderFactory; // Step 빌더 생성용
    private final DataSource dataSource;

    @Bean
    public Job conditionalStepFlowJob() {
        return jobBuilderFactory.get("conditionalStepFlowJob")
                .start(conditionStep1())
                    .on("FAILED")
                    .to(failOverStep())

                .from(conditionStep1())
                    .on("STOPPED")
                    .to(printWhenStopStep())

                .from(conditionStep1())
                    .on("COMPLETED")
                    .to(completeStep())

                .end()
                .build();
    }

    @Bean
    public Step conditionStep1() {
        return stepBuilderFactory.get("conditionStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.debug("I'm conditionStep1");
                    log.debug("set Exit Status to {}", ExitStatus.FAILED);
                    contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step printWhenStopStep() {
        return stepBuilderFactory.get("printWhenStopStep")
                .tasklet((contribution, chunkContext) -> {
                    log.debug("I'm printWhenStopStep!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step failOverStep(){
        return stepBuilderFactory.get("nextStep")
                .startLimit(10)
                .tasklet((contribution, chunkContext) -> {
                    log.info("FailOver Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step completeStep(){
        return stepBuilderFactory.get("completeStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("완료!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<ResponseAdvertisement> advertisementReader() throws Exception {
        Map<String,Object> parameterValues = new HashMap<>();
        parameterValues.put("amount", "10000");

        return new JdbcPagingItemReaderBuilder<ResponseAdvertisement>()
                .pageSize(10)
                .fetchSize(10)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(ResponseAdvertisement.class))
                .queryProvider(customQueryProvider())
                .parameterValues(parameterValues)
                .name("JdbcPagingItemReader")
                .build();
    }

    public PagingQueryProvider customQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();

        queryProviderFactoryBean.setDataSource(dataSource);

        queryProviderFactoryBean.setSelectClause("SELECT ID, NAME, EMAIL, NICK_NAME, STATUS, AMOUNT ");
        queryProviderFactoryBean.setFromClause("FROM MEMBER ");
        queryProviderFactoryBean.setWhereClause("WHERE AMOUNT >= :amount");

        Map<String,Order> sortKey = new HashMap<>();
        sortKey.put("id", Order.ASCENDING);

        queryProviderFactoryBean.setSortKeys(sortKey);

        return queryProviderFactoryBean.getObject();

    }


}
