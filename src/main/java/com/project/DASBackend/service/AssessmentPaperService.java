package com.project.DASBackend.service;

import com.project.DASBackend.dto.AssessmentPaperDto;

import java.util.List;

public interface AssessmentPaperService {
    AssessmentPaperDto createAssessmentPaper(AssessmentPaperDto assessmentPaperDto);

    AssessmentPaperDto getAssessmentPaperById(Integer diamondId);

    List<AssessmentPaperDto> getAllAssessmentPapers();

    AssessmentPaperDto updateAssessmentPaper(Integer diamondId, AssessmentPaperDto assessmentPaperDto);

    void deleteAssessmentPaper(Integer diamondId);

    AssessmentPaperDto getAssessmentPaperBySampleId(Integer sampleId);
}
