package com.charminseok.advertisement.controller;

import com.charminseok.advertisement.dto.RequestAdvertisement;
import com.charminseok.advertisement.dto.ResponseAdvertisement;
import com.charminseok.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
    @GetMapping("/advertisement")
    public ResponseEntity<List<ResponseAdvertisement>> getAdvertisementList(){
        List<ResponseAdvertisement> advertisementList = advertisementService.getAdvertisementList();
        return new ResponseEntity<>(advertisementList, HttpStatus.OK);
    }

    @PostMapping("/advertisement/bidding")
    public ResponseEntity bidAdvertisement(@RequestBody @Validated RequestAdvertisement requestAdvertisement){
        advertisementService.bidAdvertisement(requestAdvertisement);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/advertisement/cpc/{advertisementId}")
    public ResponseEntity clickAdvertisement(@PathVariable("advertisementId") Long advertisementId){
        advertisementService.clickAdvertisement(advertisementId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
