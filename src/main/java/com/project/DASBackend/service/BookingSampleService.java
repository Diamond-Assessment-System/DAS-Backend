package com.project.DASBackend.service;

import com.project.DASBackend.dto.BookingSampleDto;

import java.util.List;

public interface BookingSampleService {
    BookingSampleDto createBookingSample(BookingSampleDto bookingSampleDto);

    List<BookingSampleDto> createBookingSamples(List<BookingSampleDto> bookingSampleDtos);

    BookingSampleDto getBookingSampleById(Integer sampleId);

    List<BookingSampleDto> getAllBookingSamples();

    List<BookingSampleDto> getBookingSamplesByBookingId(Integer bookingId);

    BookingSampleDto updateBookingSample(Integer sampleId, BookingSampleDto bookingSampleDto);

    void deleteBookingSample(Integer sampleId);

    BookingSampleDto changeStatus(Integer sampleId, Integer status);

    BookingSampleDto assignStaff(Integer sampleId, Integer assessmentAccountId);

}
