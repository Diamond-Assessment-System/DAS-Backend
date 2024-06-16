package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.ServiceDto;
import com.project.DASBackend.entity.Services;

public class ServiceMapper {
    public static ServiceDto toDto(Services services) {
        if (services == null) {
            return null;
        }
        return ServiceDto.builder()
                .serviceId(services.getServiceId())
                .serviceName(services.getServiceName())
                .serviceDescription(services.getServiceDescription())
                .serviceStatus(services.getServiceStatus())
                .servicePrice(services.getServicePrice())
                .serviceTime(services.getServiceTime())
                .build();
    }

    public static Services toEntity(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        Services services = new Services();
        services.setServiceId(serviceDto.getServiceId());
        services.setServiceName(serviceDto.getServiceName());
        services.setServiceDescription(serviceDto.getServiceDescription());
        services.setServiceStatus(serviceDto.getServiceStatus());
        services.setServicePrice(serviceDto.getServicePrice());
        services.setServiceTime(serviceDto.getServiceTime());
        return services;
    }
}
