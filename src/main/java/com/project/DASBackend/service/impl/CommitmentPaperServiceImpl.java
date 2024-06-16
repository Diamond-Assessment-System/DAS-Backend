package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.CommitmentPaperDto;
import com.project.DASBackend.entity.CommitmentPaper;
import com.project.DASBackend.mapper.CommitmentPaperMapper;
import com.project.DASBackend.repository.CommitmentPaperRepository;
import com.project.DASBackend.service.CommitmentPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommitmentPaperServiceImpl implements CommitmentPaperService {

    @Autowired
    private CommitmentPaperRepository commitmentPaperRepository;

    @Override
    public CommitmentPaperDto createCommitmentPaper(CommitmentPaperDto commitmentPaperDto) {
        CommitmentPaper commitmentPaper = CommitmentPaperMapper.toEntity(commitmentPaperDto);
        commitmentPaper = commitmentPaperRepository.save(commitmentPaper);
        return CommitmentPaperMapper.toDto(commitmentPaper);
    }

    @Override
    public CommitmentPaperDto getCommitmentPaperById(Integer commitmentId) {
        CommitmentPaper commitmentPaper = commitmentPaperRepository.findById(commitmentId).orElse(null);
        return CommitmentPaperMapper.toDto(commitmentPaper);
    }

    @Override
    public List<CommitmentPaperDto> getAllCommitmentPapers() {
        List<CommitmentPaper> commitmentPapers = commitmentPaperRepository.findAll();
        return commitmentPapers.stream().map(CommitmentPaperMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CommitmentPaperDto updateCommitmentPaper(Integer commitmentId, CommitmentPaperDto commitmentPaperDto) {
        CommitmentPaper commitmentPaper = CommitmentPaperMapper.toEntity(commitmentPaperDto);
        commitmentPaper.setCommitmentId(commitmentId);
        commitmentPaper.setDescription(commitmentPaperDto.getDescription());
        commitmentPaper.setDateCreated(commitmentPaperDto.getDateCreated());
        commitmentPaper.setApprovalDate(commitmentPaperDto.getApprovalDate());
        commitmentPaper.setCommitmentType(commitmentPaperDto.getCommitmentType());
        commitmentPaper.setTitle(commitmentPaperDto.getTitle());
        commitmentPaper.setStatus(commitmentPaperDto.getStatus());
        commitmentPaper = commitmentPaperRepository.save(commitmentPaper);
        return CommitmentPaperMapper.toDto(commitmentPaper);
    }

    @Override
    public void deleteCommitmentPaper(Integer commitmentId) {
        commitmentPaperRepository.deleteById(commitmentId);
    }

    @Override
    public CommitmentPaperDto changeStatus(Integer commitmentId, Integer status) {
        CommitmentPaper commitmentPaper = commitmentPaperRepository.findById(commitmentId).orElse(null);
        if (commitmentPaper != null) {
            commitmentPaper.setStatus(status);
            commitmentPaper = commitmentPaperRepository.save(commitmentPaper);
        }
        return CommitmentPaperMapper.toDto(commitmentPaper);
    }
}
