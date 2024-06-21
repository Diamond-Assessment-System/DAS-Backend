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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        // Chuyển đổi paperImage từ Base64 sang MultipartFile
        MultipartFile multipartFile = convertBase64ToMultipartFile(assessmentPaperDto.getPaperImage());

        // Tải tệp lên AWS và nhận URL tệp
        String fileUrl;
        try {
            fileUrl = storageService.uploadFile(multipartFile);
        } catch (AmazonServiceException | SdkClientException | IOException e) {
            throw new RuntimeException("\n" +
                    "Unable to upload file to AWS S3", e);
        }
        // Đặt URL tệp vào trường paperImage
        assessmentPaperDto.setPaperImage(fileUrl);

        // Chuyển DTO thành thực thể
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
        List<AssessmentPaper> assessmentPapers = assessmentPaperRepository.findAll();
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
    // Phương thức tiện ích để chuyển đổi chuỗi Base64 sang MultipartFile
    private MultipartFile convertBase64ToMultipartFile(String base64) {
        String[] base64Parts = base64.split(",");
        String extension = base64Parts[0].split("/")[1].split(";")[0];
        byte[] data = Base64.getDecoder().decode(base64Parts[1]);
        return new Base64MultipartFile(data, base64Parts[0]);
    }
}
