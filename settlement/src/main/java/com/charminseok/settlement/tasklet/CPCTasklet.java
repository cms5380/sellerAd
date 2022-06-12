package com.charminseok.settlement.tasklet;

import com.charminseok.settlement.domain.SettlementDomain;
import com.charminseok.settlement.job.ShareDataBean;
import com.charminseok.settlement.openfeign.advertisement.dto.CPCRequestDto;
import com.charminseok.settlement.openfeign.advertisement.dto.CPCResponseDto;
import com.charminseok.settlement.openfeign.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CPCTasklet implements Tasklet, StepExecutionListener {
    private final AdvertisementService advertisementService;
    private List<CPCResponseDto> cpcCountList;
    private final ShareDataBean<List<SettlementDomain>> shareDataBean;

    @Override
    public void beforeStep(StepExecution stepExecution) {
//        String clickDate = stepExecution.getJobParameters().getParameters().get("clickDate").toString();
        String clickDate = LocalDate.now().toString();
//        String endDate = LocalDate.parse(clickDate, DateTimeFormatter.ISO_DATE).plusDays(1).toString();
        String endDate = LocalDate.now().plusDays(1).toString();
        System.out.println(clickDate);
        cpcCountList = advertisementService.getCPCCountList(new CPCRequestDto(clickDate, endDate));
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.debug("executed CPC tasklet !!");
//        String clickDate = (String) chunkContext.getStepContext()
//                .getJobParameters()
//                .get("clickDate");
        String clickDate = LocalDate.now().toString();

        List<SettlementDomain> settlementList = new ArrayList<>();
        process(clickDate, settlementList);
        write(settlementList);

        return RepeatStatus.FINISHED;
    }


    private void process(String clickDate, List<SettlementDomain> settlementList) {
        cpcCountList.forEach(cpcResponseDto -> {
            int totalBillAmount = cpcResponseDto.getClickCount() * cpcResponseDto.getAdvertisementPrice();
            settlementList.add(SettlementDomain.builder()
                    .clickDate(clickDate)
                    .advertisementId(cpcResponseDto.getAdvertisementId())
                    .clickCount(cpcResponseDto.getClickCount())
                    .companyId(cpcResponseDto.getCompanyId())
                    .productId(cpcResponseDto.getProductId())
                    .totalBillAmount(totalBillAmount)
                    .build());
        });

    }

    private void write(List<SettlementDomain> settlementList) {
        shareDataBean.putData("settlementList", settlementList);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
