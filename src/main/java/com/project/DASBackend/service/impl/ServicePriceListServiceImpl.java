package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.ServicePriceListDto;
import com.project.DASBackend.entity.BookingSample;
import com.project.DASBackend.entity.ServicePriceList;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.ServicePriceListMapper;
import com.project.DASBackend.repository.BookingSampleRepository;
import com.project.DASBackend.repository.ServicePriceListRepository;
import com.project.DASBackend.service.ServicePriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicePriceListServiceImpl implements ServicePriceListService {

    @Autowired
    private ServicePriceListRepository servicePriceListRepository;

    @Override
    public ServicePriceListDto createServicePriceList(ServicePriceListDto servicePriceListDto) {
        ServicePriceList servicePriceList = ServicePriceListMapper.toEntity(servicePriceListDto);


        servicePriceList = servicePriceListRepository.save(servicePriceList);
        return ServicePriceListMapper.toDto(servicePriceList);
    }

    @Override
    public ServicePriceListDto getServicePriceListById(Integer servicePriceId) {
        ServicePriceList servicePriceList = servicePriceListRepository.findById(servicePriceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service Price List not found with id: " + servicePriceId));
        return ServicePriceListMapper.toDto(servicePriceList);
    }

    @Override
    public List<ServicePriceListDto> getAllServicePriceLists() {
        List<ServicePriceList> servicePriceLists = servicePriceListRepository.findAll();
        return servicePriceLists.stream().map(ServicePriceListMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ServicePriceListDto updateServicePriceList(Integer servicePriceId, ServicePriceListDto servicePriceListDto) {
        ServicePriceList servicePriceList = servicePriceListRepository.findById(servicePriceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service Price List not found with id: " + servicePriceId));
        servicePriceList.setSizeFrom(servicePriceListDto.getSizeFrom());
        servicePriceList.setSizeTo(servicePriceListDto.getSizeTo());
        servicePriceList.setInitPrice(servicePriceListDto.getInitPrice());
        servicePriceList.setPriceUnit(servicePriceListDto.getPriceUnit());
        servicePriceList = servicePriceListRepository.save(servicePriceList);
        return ServicePriceListMapper.toDto(servicePriceList);
    }

    @Override
    public void deleteServicePriceList(Integer servicePriceId) {
        if (!servicePriceListRepository.existsById(servicePriceId)) {
            throw new ResourceNotFoundException("Service Price List not found with id: " + servicePriceId);
        }
        servicePriceListRepository.deleteById(servicePriceId);
    }

}
