package com.charminseok.settlement.job;

import com.charminseok.settlement.domain.SettlementDomain;
import com.charminseok.settlement.openfeign.advertisement.dto.CPCRequestDto;
import com.charminseok.settlement.openfeign.advertisement.dto.CPCResponseDto;
import com.charminseok.settlement.openfeign.advertisement.service.AdvertisementService;
import com.charminseok.settlement.openfeign.product.dto.Product;
import com.charminseok.settlement.openfeign.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AdvertisementService advertisementService;
    private final ProductService productService;
    private final ShareDataBean<List<SettlementDomain>> shareDataBean;
    private final SqlSessionFactory sqlSessionFactory;

    @Bean
    public Job settlementJob() {
        return jobBuilderFactory.get("settlementJob")
                .start(settlementStep())
                .build();
    }


    @Bean
    protected Step settlementStep() {
        return stepBuilderFactory.get("settlementStep")
                .<CPCResponseDto, SettlementDomain>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<CPCResponseDto> reader() {
        String clickDate = LocalDate.now().toString();
        String endDate = LocalDate.now().plusDays(1).toString();
        List<CPCResponseDto> cpcCountList = advertisementService.getCPCCountList(new CPCRequestDto(clickDate, endDate));

        return new ListItemReader<>(cpcCountList);
    }

    @Bean
    @StepScope
    public ItemProcessor<CPCResponseDto, SettlementDomain> processor() {
        return cpcResponseDto -> {
            int totalBillAmount = cpcResponseDto.getClickCount() * cpcResponseDto.getAdvertisementPrice();
            Product product = productService.getProduct(cpcResponseDto.getProductId());

            return SettlementDomain.builder()
                    .clickDate(LocalDate.now().toString())
                    .companyId(cpcResponseDto.getCompanyId())
                    .companyName(product.getCompanyName())
                    .advertisementId(cpcResponseDto.getAdvertisementId())
                    .productId(cpcResponseDto.getProductId())
                    .productName(product.getProductName())
                    .clickCount(cpcResponseDto.getClickCount())
                    .totalBillAmount(totalBillAmount)
                    .build();
        };
    }

    @Bean
    @StepScope
    public MyBatisBatchItemWriter<SettlementDomain> writer() {
        return new MyBatisBatchItemWriterBuilder<SettlementDomain>()
                .sqlSessionFactory(sqlSessionFactory)
                .statementId("com.charminseok.settlement.mapper.SettlementMapper.insertSettlement")
                .build();
    }
}
