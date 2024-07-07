package com.project.DASBackend.controller;

import com.project.DASBackend.dto.ServicePriceListDto;
import com.project.DASBackend.service.ServicePriceListService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/service-price-lists")
public class ServicePriceListController {

    @Autowired
    private ServicePriceListService servicePriceListService;

    @PostMapping
    public ResponseEntity<ServicePriceListDto> createServicePriceList(@Valid @RequestBody ServicePriceListDto servicePriceListDto) {
        return new ResponseEntity<>(servicePriceListService.createServicePriceList(servicePriceListDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServicePriceListDto> getServicePriceListById(@PathVariable("id") Integer servicePriceId) {
        ServicePriceListDto servicePriceListDto = servicePriceListService.getServicePriceListById(servicePriceId);
        return ResponseEntity.ok(servicePriceListDto);
    }

    @GetMapping
    public ResponseEntity<List<ServicePriceListDto>> getAllServicePriceLists() {
        List<ServicePriceListDto> servicePriceListDtos = servicePriceListService.getAllServicePriceLists();
        return ResponseEntity.ok(servicePriceListDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<ServicePriceListDto> updateServicePriceList(@PathVariable("id") Integer servicePriceId,
                                                                      @Valid @RequestBody ServicePriceListDto servicePriceListDto) {
        return ResponseEntity.ok(servicePriceListService.updateServicePriceList(servicePriceId, servicePriceListDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ServicePriceListDto> updateServicePriceListPrice(@PathVariable("id") Integer servicePriceId,
                                                                           @RequestParam("initPrice") Float initPrice,
                                                                           @RequestParam("priceUnit") Float priceUnit) {
        return ResponseEntity.ok(servicePriceListService.updateServicePriceListPrice(servicePriceId, initPrice, priceUnit));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteServicePriceList(@PathVariable("id") Integer servicePriceId) {
        servicePriceListService.deleteServicePriceList(servicePriceId);
        return ResponseEntity.ok("Service Price List deleted successfully");
    }
}
