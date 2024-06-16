package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.ServicePriceListDto;
import com.project.DASBackend.entity.ServicePriceList;

public class ServicePriceListMapper {
    public static ServicePriceListDto toDto(ServicePriceList servicePriceList) {
        if (servicePriceList == null) {
            return null;
        }
        return ServicePriceListDto.builder()
                .servicePriceId(servicePriceList.getServicePriceId())
                .sizeFrom(servicePriceList.getSizeFrom())
                .sizeTo(servicePriceList.getSizeTo())
                .initPrice(servicePriceList.getInitPrice())
                .priceUnit(servicePriceList.getPriceUnit())
                .build();
    }

    public static ServicePriceList toEntity(ServicePriceListDto servicePriceListDto) {
        if (servicePriceListDto == null) {
            return null;
        }
        ServicePriceList servicePriceList = new ServicePriceList();
        servicePriceList.setServicePriceId(servicePriceListDto.getServicePriceId());
        servicePriceList.setSizeFrom(servicePriceListDto.getSizeFrom());
        servicePriceList.setSizeTo(servicePriceListDto.getSizeTo());
        servicePriceList.setInitPrice(servicePriceListDto.getInitPrice());
        servicePriceList.setPriceUnit(servicePriceListDto.getPriceUnit());
        return servicePriceList;
    }
}
