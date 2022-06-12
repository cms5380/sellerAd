package com.charminseok.settlement.openfeign.advertisement.service;

import com.charminseok.settlement.openfeign.advertisement.dto.CPCRequestDto;
import com.charminseok.settlement.openfeign.advertisement.dto.CPCResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "advertisement")
public interface AdvertisementService {
    @GetMapping("/advertisement/cpc")
    List<CPCResponseDto> getCPCCountList(@SpringQueryMap CPCRequestDto cpcRequestDto);
}
