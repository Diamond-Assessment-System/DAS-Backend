package com.project.DASBackend.controller;

import com.project.DASBackend.dto.ServiceDto;
import com.project.DASBackend.service.ServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ServiceDto> createService(@Valid @RequestBody ServiceDto serviceDto) {
        return new ResponseEntity<>(serviceService.createService(serviceDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable("id") Integer serviceId) {
        ServiceDto serviceDto = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(serviceDto);
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<ServiceDto> serviceDtos = serviceService.getAllServices();
        return ResponseEntity.ok(serviceDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<ServiceDto> updateService(@Valid @RequestBody ServiceDto serviceDto,
                                                    @PathVariable("id") Integer serviceId) {
        return ResponseEntity.ok(serviceService.updateService(serviceId, serviceDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteService(@PathVariable("id") Integer serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.ok("Service deleted successfully");
    }

    @PutMapping("/{serviceId}/update-account")
    public ResponseEntity<String> updateAccountId(@PathVariable Integer serviceId, @RequestParam Integer accountId) {
        serviceService.updateAccountId(serviceId, accountId);
        return ResponseEntity.ok("Success");
    }
}
