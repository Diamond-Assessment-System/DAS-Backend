package com.project.DASBackend.service.impl;

import com.amazonaws.AmazonServiceException;
import com.project.DASBackend.dto.AssessmentPaperDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.entity.AssessmentPaper;
import com.project.DASBackend.entity.BookingSample;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.AssessmentPaperMapper;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.repository.AssessmentPaperRepository;
import com.project.DASBackend.repository.BookingSampleRepository;
import com.project.DASBackend.resource.Base64MultipartFile;
import com.project.DASBackend.service.AssessmentPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.io.IOException;
import java.util.Base64;
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

    @Autowired
    private StorageService storageService;

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

    public static MultipartFile convertBase64ToMultipartFile(String base64) {
        // Tách chuỗi base64 thành các phần
        String[] base64Parts = base64.split(",");
        // Kiểm tra nếu base64Parts có ít nhất 2 phần
        if (base64Parts.length != 2) {
            throw new IllegalArgumentException("Invalid base64 string");
        }
        try {
            // Giải mã dữ liệu base64
            byte[] data = Base64.getDecoder().decode(base64Parts[1]);
            // Tạo một MultipartFile mới
            return new Base64MultipartFile(data, base64Parts[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert base64 string to MultipartFile", e);
        }
    }

}
