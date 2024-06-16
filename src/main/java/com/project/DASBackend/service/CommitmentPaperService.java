package com.project.DASBackend.service;

import com.project.DASBackend.dto.CommitmentPaperDto;

import java.util.List;

public interface CommitmentPaperService {
    CommitmentPaperDto createCommitmentPaper(CommitmentPaperDto commitmentPaperDto);

    CommitmentPaperDto getCommitmentPaperById(Integer commitmentId);

    List<CommitmentPaperDto> getAllCommitmentPapers();

    CommitmentPaperDto updateCommitmentPaper(Integer commitmentId, CommitmentPaperDto commitmentPaperDto);

    void deleteCommitmentPaper(Integer commitmentId);
}
