package com.project.DASBackend.service;

import com.project.DASBackend.dto.ServiceDto;

import java.util.List;

public interface ServiceService {
    ServiceDto createService(ServiceDto serviceDto);

    ServiceDto getServiceById(Integer serviceId);

    List<ServiceDto> getAllServices();

    ServiceDto updateService(Integer serviceId, ServiceDto serviceDto);

    void deleteService(Integer serviceId);

    ServiceDto changeStatus(Integer serviceId, Integer status);
}
