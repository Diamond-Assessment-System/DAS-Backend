package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.BookingSampleDto;
import com.project.DASBackend.entity.BookingSample;

public class BookingSampleMapper {
    public static BookingSampleDto toDto(BookingSample bookingSample) {
        if (bookingSample == null) {
            return null;
        }
        return BookingSampleDto.builder()
                .sampleId(bookingSample.getSampleId())
                .status(bookingSample.getStatus())
                .isDiamond(bookingSample.getIsDiamond())
                .name(bookingSample.getName())
                .size(bookingSample.getSize())
                .price(bookingSample.getPrice())
                .diamondId(bookingSample.getAssessmentPaper().getDiamondId())
                .bookingId(bookingSample.getAssessmentBooking().getBookingId())
                .accountId(bookingSample.getAccount().getAccountId())
                .sealId(bookingSample.getSeal().getSealId())
                .build();
    }

    public static BookingSample toEntity(BookingSampleDto bookingSampleDto) {
        if (bookingSampleDto == null) {
            return null;
        }
        BookingSample bookingSample = new BookingSample();
        bookingSample.setSampleId(bookingSampleDto.getSampleId());
        bookingSample.setStatus(bookingSampleDto.getStatus());
        bookingSample.setIsDiamond(bookingSampleDto.getIsDiamond());
        bookingSample.setName(bookingSampleDto.getName());
        bookingSample.setSize(bookingSampleDto.getSize());
        bookingSample.setPrice(bookingSampleDto.getPrice());
        // Note: diamondId, bookingId, accountId, and sealId mapping should be handled separately based on your application logic
        return bookingSample;
    }
}
