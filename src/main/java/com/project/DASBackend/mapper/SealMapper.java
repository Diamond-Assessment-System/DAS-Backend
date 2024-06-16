package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.SealDto;
import com.project.DASBackend.entity.Seal;

public class SealMapper {
    public static SealDto toDto(Seal seal) {
        if (seal == null) {
            return null;
        }
        return SealDto.builder()
                .sealId(seal.getSealId())
                .shape(seal.getShape())
                .weight(seal.getWeight())
                .size(seal.getSize())
                .dateCreated(seal.getDateCreated())
                .color(seal.getColor())
                .clarity(seal.getClarity())
                .sampleId(seal.getBookingSample().getSampleId())
                .build();
    }

    public static Seal toEntity(SealDto sealDto) {
        if (sealDto == null) {
            return null;
        }
        Seal seal = new Seal();
        seal.setSealId(sealDto.getSealId());
        seal.setShape(sealDto.getShape());
        seal.setWeight(sealDto.getWeight());
        seal.setSize(sealDto.getSize());
        seal.setDateCreated(sealDto.getDateCreated());
        seal.setColor(sealDto.getColor());
        seal.setClarity(sealDto.getClarity());
        // BookingSample needs to be set in the service layer
        return seal;
    }
}
