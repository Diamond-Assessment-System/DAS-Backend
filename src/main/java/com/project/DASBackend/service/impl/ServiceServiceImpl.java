package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.ServiceDto;
import com.project.DASBackend.entity.Services;
import com.project.DASBackend.mapper.ServiceMapper;
import com.project.DASBackend.repository.ServiceRepository;
import com.project.DASBackend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ServiceDto createService(ServiceDto serviceDto) {
        Services services = ServiceMapper.toEntity(serviceDto);
        services = serviceRepository.save(services);
        return ServiceMapper.toDto(services);
    }

    @Override
    public ServiceDto getServiceById(Integer serviceId) {
        Services services = serviceRepository.findById(serviceId).orElse(null);
        return ServiceMapper.toDto(services);
    }

    @Override
    public List<ServiceDto> getAllServices() {
        List<Services> services = serviceRepository.findAll();
        return services.stream().map(ServiceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ServiceDto updateService(Integer serviceId, ServiceDto serviceDto) {
        Services services = ServiceMapper.toEntity(serviceDto);
        services.setServiceId(serviceId);
        services = serviceRepository.save(services);
        return ServiceMapper.toDto(services);
    }

    @Override
    public void deleteService(Integer serviceId) {
        serviceRepository.deleteById(serviceId);
    }
}
