package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.ServiceDto;
import com.project.DASBackend.entity.Services;

public class ServiceMapper {
    public static ServiceDto toDto(Services service) {
        if (service == null) {
            return null;
        }
        return ServiceDto.builder()
                .serviceId(service.getServiceId())
                .serviceName(service.getServiceName())
                .serviceDescription(service.getServiceDescription())
                .serviceStatus(service.getServiceStatus())
                .servicePrice(service.getServicePrice())
                .serviceType(service.getServiceType())
                .serviceTime(service.getServiceTime())
                .build();
    }

    public static Services toEntity(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        Services service = new Services();
        service.setServiceId(serviceDto.getServiceId());
        service.setServiceName(serviceDto.getServiceName());
        service.setServiceDescription(serviceDto.getServiceDescription());
        service.setServiceStatus(serviceDto.getServiceStatus());
        service.setServiceType(serviceDto.getServiceType());
        service.setServicePrice(serviceDto.getServicePrice());
        service.setServiceTime(serviceDto.getServiceTime());
        return service;
    }
}
