package com.charminseok.settlement.mapper;

import com.charminseok.settlement.domain.SettlementDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettlementMapper {
    int insertSettlement(List<SettlementDomain> settlementList);
}
