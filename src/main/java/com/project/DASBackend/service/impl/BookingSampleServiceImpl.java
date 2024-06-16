package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.BookingSampleDto;
import com.project.DASBackend.entity.BookingSample;
import com.project.DASBackend.mapper.BookingSampleMapper;
import com.project.DASBackend.repository.BookingSampleRepository;
import com.project.DASBackend.service.BookingSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingSampleServiceImpl implements BookingSampleService {

    @Autowired
    private BookingSampleRepository bookingSampleRepository;

    @Override
    public BookingSampleDto createBookingSample(BookingSampleDto bookingSampleDto) {
        BookingSample bookingSample = BookingSampleMapper.toEntity(bookingSampleDto);
        bookingSample = bookingSampleRepository.save(bookingSample);
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public BookingSampleDto getBookingSampleById(Integer sampleId) {
        BookingSample bookingSample = bookingSampleRepository.findById(sampleId).orElse(null);
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public List<BookingSampleDto> getAllBookingSamples() {
        List<BookingSample> bookingSamples = bookingSampleRepository.findAll();
        return bookingSamples.stream().map(BookingSampleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookingSampleDto updateBookingSample(Integer sampleId, BookingSampleDto bookingSampleDto) {
        BookingSample bookingSample = BookingSampleMapper.toEntity(bookingSampleDto);
        bookingSample.setSampleId(sampleId);
        bookingSample.setStatus(bookingSampleDto.getStatus());
        bookingSample.setIsDiamond(bookingSampleDto.getIsDiamond());
        bookingSample.setName(bookingSampleDto.getName());
        bookingSample.setSize(bookingSampleDto.getSize());
        bookingSample.setPrice(bookingSampleDto.getPrice());
        bookingSample = bookingSampleRepository.save(bookingSample);
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public void deleteBookingSample(Integer sampleId) {
        bookingSampleRepository.deleteById(sampleId);
    }

    @Override
    public BookingSampleDto changeStatus(Integer sampleId, Integer status) {
        BookingSample bookingSample = bookingSampleRepository.findById(sampleId).orElse(null);
        if (bookingSample != null) {
            bookingSample.setStatus(status);
            bookingSample = bookingSampleRepository.save(bookingSample);
        }
        return BookingSampleMapper.toDto(bookingSample);
    }
}
