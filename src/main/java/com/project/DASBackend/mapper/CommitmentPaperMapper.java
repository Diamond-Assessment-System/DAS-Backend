package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.CommitmentPaperDto;
import com.project.DASBackend.entity.CommitmentPaper;

public class CommitmentPaperMapper {
    public static CommitmentPaperDto toDto(CommitmentPaper commitmentPaper) {
        if (commitmentPaper == null) {
            return null;
        }
        return CommitmentPaperDto.builder()
                .commitmentId(commitmentPaper.getCommitmentId())
                .description(commitmentPaper.getDescription())
                .dateCreated(commitmentPaper.getDateCreated())
                .approvalDate(commitmentPaper.getApprovalDate())
                .commitmentType(commitmentPaper.getCommitmentType())
                .title(commitmentPaper.getTitle())
                .status(commitmentPaper.getStatus())
                .bookingId(commitmentPaper.getAssessmentBooking().getBookingId())
                .accountId(commitmentPaper.getAccount().getAccountId())
                .build();
    }

    public static CommitmentPaper toEntity(CommitmentPaperDto commitmentPaperDto) {
        if (commitmentPaperDto == null) {
            return null;
        }
        CommitmentPaper commitmentPaper = new CommitmentPaper();
        commitmentPaper.setCommitmentId(commitmentPaperDto.getCommitmentId());
        commitmentPaper.setDescription(commitmentPaperDto.getDescription());
        commitmentPaper.setDateCreated(commitmentPaperDto.getDateCreated());
        commitmentPaper.setApprovalDate(commitmentPaperDto.getApprovalDate());
        commitmentPaper.setCommitmentType(commitmentPaperDto.getCommitmentType());
        commitmentPaper.setTitle(commitmentPaperDto.getTitle());
        commitmentPaper.setStatus(commitmentPaperDto.getStatus());
        // AssessmentBooking and Account need to be set in the service layer
        return commitmentPaper;
    }
}
