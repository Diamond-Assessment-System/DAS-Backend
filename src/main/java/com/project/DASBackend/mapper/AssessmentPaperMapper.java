package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.AssessmentPaperDto;
import com.project.DASBackend.entity.AssessmentPaper;

public class AssessmentPaperMapper {
    public static AssessmentPaperDto toDto(AssessmentPaper assessmentPaper) {
        if (assessmentPaper == null) {
            return null;
        }
        return AssessmentPaperDto.builder()
                .diamondId(assessmentPaper.getDiamondId())
                .type(assessmentPaper.getType())
                .size(assessmentPaper.getSize())
                .shape(assessmentPaper.getShape())
                .color(assessmentPaper.getColor())
                .clarity(assessmentPaper.getClarity())
                .polish(assessmentPaper.getPolish())
                .symmetry(assessmentPaper.getSymmetry())
                .fluorescence(assessmentPaper.getFluorescence())
                .weight(assessmentPaper.getWeight())
                .comments(assessmentPaper.getComments())
                .dateCreated(assessmentPaper.getDateCreated())
                .paperImage(assessmentPaper.getPaperImage())
                .accountId(assessmentPaper.getAccount().getAccountId())
                .sampleId(assessmentPaper.getBookingSample().getSampleId())
                .build();
    }

    public static AssessmentPaper toEntity(AssessmentPaperDto assessmentPaperDto) {
        if (assessmentPaperDto == null) {
            return null;
        }
        AssessmentPaper assessmentPaper = new AssessmentPaper();
        assessmentPaper.setDiamondId(assessmentPaperDto.getDiamondId());
        assessmentPaper.setType(assessmentPaperDto.getType());
        assessmentPaper.setSize(assessmentPaperDto.getSize());
        assessmentPaper.setShape(assessmentPaperDto.getShape());
        assessmentPaper.setColor(assessmentPaperDto.getColor());
        assessmentPaper.setClarity(assessmentPaperDto.getClarity());
        assessmentPaper.setPolish(assessmentPaperDto.getPolish());
        assessmentPaper.setSymmetry(assessmentPaperDto.getSymmetry());
        assessmentPaper.setFluorescence(assessmentPaperDto.getFluorescence());
        assessmentPaper.setWeight(assessmentPaperDto.getWeight());
        assessmentPaper.setComments(assessmentPaperDto.getComments());
        assessmentPaper.setDateCreated(assessmentPaperDto.getDateCreated());
        assessmentPaper.setPaperImage(assessmentPaperDto.getPaperImage());
        // Account and BookingSample need to be set in the service layer
        return assessmentPaper;
    }
}
