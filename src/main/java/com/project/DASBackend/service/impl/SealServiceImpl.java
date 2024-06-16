package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.SealDto;
import com.project.DASBackend.entity.Seal;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.SealMapper;
import com.project.DASBackend.repository.SealRepository;
import com.project.DASBackend.service.SealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SealServiceImpl implements SealService {

    @Autowired
    private SealRepository sealRepository;

    @Override
    public SealDto createSeal(SealDto sealDto) {
        Seal seal = SealMapper.toEntity(sealDto);
        seal = sealRepository.save(seal);
        return SealMapper.toDto(seal);
    }

    @Override
    public SealDto getSealById(Integer sealId) {
        Seal seal = sealRepository.findById(sealId)
                .orElseThrow(() -> new ResourceNotFoundException("Seal not found with id: " + sealId));
        return SealMapper.toDto(seal);
    }

    @Override
    public List<SealDto> getAllSeals() {
        List<Seal> seals = sealRepository.findAll();
        return seals.stream().map(SealMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SealDto updateSeal(Integer sealId, SealDto sealDto) {
        Seal seal = sealRepository.findById(sealId)
                .orElseThrow(() -> new ResourceNotFoundException("Seal not found with id: " + sealId));
        seal.setShape(sealDto.getShape());
        seal.setWeight(sealDto.getWeight());
        seal.setSize(sealDto.getSize());
        seal.setDateCreated(sealDto.getDateCreated());
        seal.setColor(sealDto.getColor());
        seal.setClarity(sealDto.getClarity());
        seal = sealRepository.save(seal);
        return SealMapper.toDto(seal);
    }

    @Override
    public void deleteSeal(Integer sealId) {
        if (!sealRepository.existsById(sealId)) {
            throw new ResourceNotFoundException("Seal not found with id: " + sealId);
        }
        sealRepository.deleteById(sealId);
    }


}
