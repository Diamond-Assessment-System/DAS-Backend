package com.project.DASBackend.service;

import com.project.DASBackend.dto.SealDto;

import java.util.List;

public interface SealService {
    SealDto createSeal(SealDto sealDto);

    SealDto getSealById(Integer sealId);

    List<SealDto> getAllSeals();

    SealDto updateSeal(Integer sealId, SealDto sealDto);

    void deleteSeal(Integer sealId);
}
