package com.project.DASBackend.controller;

import com.project.DASBackend.dto.CommitmentPaperDto;
import com.project.DASBackend.service.CommitmentPaperService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/commitment-papers")
public class CommitmentPaperController {

    @Autowired
    private CommitmentPaperService commitmentPaperService;

    @PostMapping
    public ResponseEntity<CommitmentPaperDto> createCommitmentPaper(@Valid @RequestBody CommitmentPaperDto commitmentPaperDto) {
        return new ResponseEntity<>(commitmentPaperService.createCommitmentPaper(commitmentPaperDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommitmentPaperDto> getCommitmentPaperById(@PathVariable("id") Integer commitmentId) {
        CommitmentPaperDto commitmentPaperDto = commitmentPaperService.getCommitmentPaperById(commitmentId);
        return ResponseEntity.ok(commitmentPaperDto);
    }

    @GetMapping
    public ResponseEntity<List<CommitmentPaperDto>> getAllCommitmentPapers() {
        List<CommitmentPaperDto> commitmentPaperDtos = commitmentPaperService.getAllCommitmentPapers();
        return ResponseEntity.ok(commitmentPaperDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommitmentPaperDto> updateCommitmentPaper(@Valid @RequestBody CommitmentPaperDto commitmentPaperDto,
                                                                    @PathVariable("id") Integer commitmentId) {
        return ResponseEntity.ok(commitmentPaperService.updateCommitmentPaper(commitmentId, commitmentPaperDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommitmentPaper(@PathVariable("id") Integer commitmentId) {
        commitmentPaperService.deleteCommitmentPaper(commitmentId);
        return ResponseEntity.ok("Commitment Paper deleted successfully");
    }
}
