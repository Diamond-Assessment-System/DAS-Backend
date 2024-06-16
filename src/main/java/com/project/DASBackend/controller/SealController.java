package com.project.DASBackend.controller;

import com.project.DASBackend.dto.SealDto;
import com.project.DASBackend.service.SealService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/seals")
public class SealController {

    @Autowired
    private SealService sealService;

    @PostMapping
    public ResponseEntity<SealDto> createSeal(@Valid @RequestBody SealDto sealDto) {
        return new ResponseEntity<>(sealService.createSeal(sealDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<SealDto> getSealById(@PathVariable("id") Integer sealId) {
        SealDto sealDto = sealService.getSealById(sealId);
        return ResponseEntity.ok(sealDto);
    }

    @GetMapping
    public ResponseEntity<List<SealDto>> getAllSeals() {
        List<SealDto> sealDtos = sealService.getAllSeals();
        return ResponseEntity.ok(sealDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<SealDto> updateSeal(@Valid @RequestBody SealDto sealDto,
                                              @PathVariable("id") Integer sealId) {
        return ResponseEntity.ok(sealService.updateSeal(sealId, sealDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeal(@PathVariable("id") Integer sealId) {
        sealService.deleteSeal(sealId);
        return ResponseEntity.ok("Seal deleted successfully");
    }
}
