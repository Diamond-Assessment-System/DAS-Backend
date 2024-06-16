package com.project.DASBackend.service;

import com.project.DASBackend.dto.AssessmentBookingDto;

import java.util.List;

public interface AssessmentBookingService {
    AssessmentBookingDto createAssessmentBooking(AssessmentBookingDto assessmentBookingDto);

    AssessmentBookingDto getAssessmentBookingById(Integer bookingId);

    List<AssessmentBookingDto> getAllAssessmentBookings();

    AssessmentBookingDto updateAssessmentBooking(Integer bookingId, AssessmentBookingDto assessmentBookingDto);

    void deleteAssessmentBooking(Integer bookingId);
}
