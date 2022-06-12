package com.charminseok.settlement.tasklet;

import com.charminseok.settlement.domain.SettlementDomain;
import com.charminseok.settlement.job.ShareDataBean;
import com.charminseok.settlement.mapper.SettlementMapper;
import com.charminseok.settlement.openfeign.product.dto.Product;
import com.charminseok.settlement.openfeign.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class ProductTasklet implements Tasklet {
    private final ProductService productService;
    private final ShareDataBean<List<SettlementDomain>> shareDataBean;
    private final SettlementMapper settlementMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        log.debug("executed product tasklet !!");
        List<SettlementDomain> settlementList = read();
        settlementList = process(settlementList);
        write(settlementList);

        return RepeatStatus.FINISHED;
    }

    private List<SettlementDomain> read() {
        return shareDataBean.getData("settlementList");
    }

    private List<SettlementDomain> process(List<SettlementDomain> settlementList) {
        return settlementList.stream().peek(settlement -> {
            Product product = productService.getProduct(settlement.getProductId());
            settlement.setProductName(product.getProductName());
            settlement.setCompanyName(product.getCompanyName());
        }).collect(Collectors.toList());
    }

    private void write(List<SettlementDomain> settlementList) {
        settlementMapper.insertSettlement(settlementList);
    }

}
