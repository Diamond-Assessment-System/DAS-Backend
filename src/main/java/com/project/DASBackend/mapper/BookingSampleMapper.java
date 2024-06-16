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
                .bookingId(bookingSample.getAssessmentBooking().getBookingId())
                .servicePriceId(bookingSample.getServicePriceList().getServicePriceId())
                .accountId(bookingSample.getAccount().getAccountId())
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
        // AssessmentBooking and Account need to be set in the service layer
        return bookingSample;
    }
}
