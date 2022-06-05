package com.charminsek.advertisement.controller;

import com.charminsek.advertisement.dto.AdvertisementDTO;
import com.charminsek.advertisement.dto.AdvertisementResponse;
import com.charminsek.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @GetMapping
    public ResponseEntity<List<AdvertisementResponse>> getAdvertisementList(){
        List<AdvertisementResponse> advertisementList = advertisementService.getAdvertisementList();
        return new ResponseEntity<>(advertisementList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity bidAdvertisement(@RequestBody AdvertisementDTO advertisementDTO){
        advertisementService.bidAdvertisement(advertisementDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/cpc/{advertisementId}")
    public ResponseEntity clickAdvertisement(@PathVariable("advertisementId") Long advertisementId){
        advertisementService.clickAdvertisement(advertisementId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
