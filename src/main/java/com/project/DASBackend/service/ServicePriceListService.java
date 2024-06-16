package com.project.DASBackend.service;

import com.project.DASBackend.dto.ServicePriceListDto;

import java.util.List;

public interface ServicePriceListService {
    ServicePriceListDto createServicePriceList(ServicePriceListDto servicePriceListDto);

    ServicePriceListDto getServicePriceListById(Integer servicePriceId);

    List<ServicePriceListDto> getAllServicePriceLists();

    ServicePriceListDto updateServicePriceList(Integer servicePriceId, ServicePriceListDto servicePriceListDto);

    void deleteServicePriceList(Integer servicePriceId);
}
