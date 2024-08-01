package com.project.DASBackend.controller;

import com.project.DASBackend.dto.AssessmentPaperDto;
import com.project.DASBackend.service.AssessmentPaperService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/assessment-papers")
public class AssessmentPaperController {

    @Autowired
    private AssessmentPaperService assessmentPaperService;

    @PostMapping
    public ResponseEntity<AssessmentPaperDto> createAssessmentPaper(@Valid @RequestBody AssessmentPaperDto assessmentPaperDto) {
        return new ResponseEntity<>(assessmentPaperService.createAssessmentPaper(assessmentPaperDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssessmentPaperDto> getAssessmentPaperById(@PathVariable("id") Integer diamondId) {
        AssessmentPaperDto assessmentPaperDto = assessmentPaperService.getAssessmentPaperById(diamondId);
        return ResponseEntity.ok(assessmentPaperDto);
    }

    @GetMapping
    public ResponseEntity<List<AssessmentPaperDto>> getAllAssessmentPapers() {
        List<AssessmentPaperDto> assessmentPaperDtos = assessmentPaperService.getAllAssessmentPapers();
        return ResponseEntity.ok(assessmentPaperDtos);
    }

    @GetMapping("{id}/sample")
    public ResponseEntity<AssessmentPaperDto> getAssessmentPaperBySampleId(@PathVariable("id") Integer sampleId) {
        AssessmentPaperDto assessmentPaperDto = assessmentPaperService.getAssessmentPaperBySampleId(sampleId);
        return ResponseEntity.ok(assessmentPaperDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<AssessmentPaperDto> updateAssessmentPaper(@Valid @RequestBody AssessmentPaperDto assessmentPaperDto,
                                                                    @PathVariable("id") Integer diamondId) {
        return ResponseEntity.ok(assessmentPaperService.updateAssessmentPaper(diamondId, assessmentPaperDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAssessmentPaper(@PathVariable("id") Integer diamondId) {
        assessmentPaperService.deleteAssessmentPaper(diamondId);
        return ResponseEntity.ok("Assessment Paper deleted successfully");
    }
}
