package com.charminseok.advertisement.controller;

import com.charminseok.advertisement.domain.AdvertisementDomain;
import com.charminseok.advertisement.domain.CPCTargetDomain;
import com.charminseok.advertisement.dto.CPCCountRequestDto;
import com.charminseok.advertisement.dto.CPCCountResponseDto;
import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.service.AdvertisementService;
import com.charminseok.advertisement.service.Impl.AdvertisementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdvertisementController {
    private final AdvertisementServiceImpl advertisementService;

    @GetMapping("/advertisements")
    public ResponseEntity<List<ResponseAdvertisement>> getAdvertisementList() {
        List<ResponseAdvertisement> advertisementList = advertisementService.selectAdvertisementTop3List();
        return new ResponseEntity<>(advertisementList, HttpStatus.OK);
    }

    @GetMapping("/advertisement/{advertisementId}")
    public ResponseEntity<AdvertisementDomain> getAdvertisement(@PathVariable Long advertisementId, @ModelAttribute RequestAdvertisement requestAdvertisement) {
        AdvertisementDomain item = advertisementService.selectAdvertisement(advertisementId, requestAdvertisement);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/advertisement")
    public ResponseEntity<AdvertisementDomain> bidAdvertisement(@RequestBody @Validated RequestAdvertisement requestAdvertisement) {
        AdvertisementDomain item = advertisementService.insertAdvertisement(requestAdvertisement);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/advertisement/{advertisementId}/cpc")
    public ResponseEntity<CPCTargetDomain> clickAdvertisement(@PathVariable("advertisementId") Long advertisementId) {
        CPCTargetDomain item = advertisementService.clickAdvertisement(advertisementId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/advertisement/cpc")
    public ResponseEntity<List<CPCCountResponseDto>> selectCPCCountList(@ModelAttribute CPCCountRequestDto cpcCountRequestDto){
        List<CPCCountResponseDto> cpcCountResponseDto = advertisementService.selectCPCCountList(cpcCountRequestDto);
        return new ResponseEntity<>(cpcCountResponseDto, HttpStatus.OK);
    }

}
