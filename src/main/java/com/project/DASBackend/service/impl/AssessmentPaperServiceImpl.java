package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.AssessmentPaperDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.entity.AssessmentPaper;
import com.project.DASBackend.entity.BookingSample;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.AssessmentPaperMapper;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.repository.AssessmentPaperRepository;
import com.project.DASBackend.repository.BookingSampleRepository;
import com.project.DASBackend.service.AssessmentPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentPaperServiceImpl implements AssessmentPaperService {

    @Autowired
    private AssessmentPaperRepository assessmentPaperRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BookingSampleRepository bookingSampleRepository;

    @Override
    public AssessmentPaperDto createAssessmentPaper(AssessmentPaperDto assessmentPaperDto) {
        AssessmentPaper assessmentPaper = AssessmentPaperMapper.toEntity(assessmentPaperDto);

        Account account = accountRepository.findById(assessmentPaperDto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + assessmentPaperDto.getAccountId()));
        assessmentPaper.setAccount(account);

        BookingSample bookingSample = bookingSampleRepository.findById(assessmentPaperDto.getSampleId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking Sample not found with id: " + assessmentPaperDto.getSampleId()));
        assessmentPaper.setBookingSample(bookingSample);


        assessmentPaper = assessmentPaperRepository.save(assessmentPaper);
        return AssessmentPaperMapper.toDto(assessmentPaper);
    }

    @Override
    public AssessmentPaperDto getAssessmentPaperById(Integer diamondId) {
        AssessmentPaper assessmentPaper = assessmentPaperRepository.findById(diamondId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment Paper not found with id: " + diamondId));
        return AssessmentPaperMapper.toDto(assessmentPaper);
    }

    @Override
    public List<AssessmentPaperDto> getAllAssessmentPapers() {
        List<AssessmentPaper> assessmentPapers = assessmentPaperRepository.findAll(Sort.by(Sort.Direction.DESC, "dateCreated"));
        return assessmentPapers.stream().map(AssessmentPaperMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AssessmentPaperDto updateAssessmentPaper(Integer diamondId, AssessmentPaperDto assessmentPaperDto) {
        AssessmentPaper assessmentPaper = assessmentPaperRepository.findById(diamondId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment Paper not found with id: " + diamondId));
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
        assessmentPaper = assessmentPaperRepository.save(assessmentPaper);
        return AssessmentPaperMapper.toDto(assessmentPaper);
    }

    @Override
    public void deleteAssessmentPaper(Integer diamondId) {
        if (!assessmentPaperRepository.existsById(diamondId)) {
            throw new ResourceNotFoundException("Assessment Paper not found with id: " + diamondId);
        }
        assessmentPaperRepository.deleteById(diamondId);
    }

}
