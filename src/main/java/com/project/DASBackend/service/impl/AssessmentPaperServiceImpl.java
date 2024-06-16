package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.AssessmentPaperDto;
import com.project.DASBackend.entity.AssessmentPaper;
import com.project.DASBackend.mapper.AssessmentPaperMapper;
import com.project.DASBackend.repository.AssessmentPaperRepository;
import com.project.DASBackend.service.AssessmentPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentPaperServiceImpl implements AssessmentPaperService {

    @Autowired
    private AssessmentPaperRepository assessmentPaperRepository;

    @Override
    public AssessmentPaperDto createAssessmentPaper(AssessmentPaperDto assessmentPaperDto) {
        AssessmentPaper assessmentPaper = AssessmentPaperMapper.toEntity(assessmentPaperDto);
        assessmentPaper = assessmentPaperRepository.save(assessmentPaper);
        return AssessmentPaperMapper.toDto(assessmentPaper);
    }

    @Override
    public AssessmentPaperDto getAssessmentPaperById(Integer diamondId) {
        AssessmentPaper assessmentPaper = assessmentPaperRepository.findById(diamondId).orElse(null);
        return AssessmentPaperMapper.toDto(assessmentPaper);
    }

    @Override
    public List<AssessmentPaperDto> getAllAssessmentPapers() {
        List<AssessmentPaper> assessmentPapers = assessmentPaperRepository.findAll();
        return assessmentPapers.stream().map(AssessmentPaperMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AssessmentPaperDto updateAssessmentPaper(Integer diamondId, AssessmentPaperDto assessmentPaperDto) {
        AssessmentPaper assessmentPaper = AssessmentPaperMapper.toEntity(assessmentPaperDto);
        assessmentPaper.setDiamondId(diamondId);
        assessmentPaper = assessmentPaperRepository.save(assessmentPaper);
        return AssessmentPaperMapper.toDto(assessmentPaper);
    }

    @Override
    public void deleteAssessmentPaper(Integer diamondId) {
        assessmentPaperRepository.deleteById(diamondId);
    }
}
