package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.ServiceDto;
import com.project.DASBackend.entity.Services;
import com.project.DASBackend.exception.ResourceNotFoundException;
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
        Services service = ServiceMapper.toEntity(serviceDto);
        service = serviceRepository.save(service);
        return ServiceMapper.toDto(service);
    }

    @Override
    public ServiceDto getServiceById(Integer serviceId) {
        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + serviceId));
        return ServiceMapper.toDto(service);
    }

    @Override
    public List<ServiceDto> getAllServices() {
        List<Services> services = serviceRepository.findAll();
        return services.stream().map(ServiceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ServiceDto updateService(Integer serviceId, ServiceDto serviceDto) {
        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + serviceId));
        service.setServiceName(serviceDto.getServiceName());
        service.setServiceDescription(serviceDto.getServiceDescription());
        service.setServiceStatus(serviceDto.getServiceStatus());
        service.setServicePrice(serviceDto.getServicePrice());
        service.setServiceTime(serviceDto.getServiceTime());
        service = serviceRepository.save(service);
        return ServiceMapper.toDto(service);
    }

    @Override
    public void deleteService(Integer serviceId) {
        if (!serviceRepository.existsById(serviceId)) {
            throw new ResourceNotFoundException("Service not found with id: " + serviceId);
        }
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public ServiceDto changeStatus(Integer serviceId, Integer status) {
        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + serviceId));
        service.setServiceStatus(status);  // Assuming Service entity has a serviceStatus field
        service = serviceRepository.save(service);
        return ServiceMapper.toDto(service);
    }
}
