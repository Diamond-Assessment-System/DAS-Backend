package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.AssessmentBookingDto;
import com.project.DASBackend.entity.AssessmentBooking;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.AssessmentBookingMapper;
import com.project.DASBackend.repository.AssessmentBookingRepository;
import com.project.DASBackend.service.AssessmentBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentBookingServiceImpl implements AssessmentBookingService {

    @Autowired
    private AssessmentBookingRepository assessmentBookingRepository;

    @Override
    public AssessmentBookingDto createAssessmentBooking(AssessmentBookingDto assessmentBookingDto) {
        AssessmentBooking assessmentBooking = AssessmentBookingMapper.toEntity(assessmentBookingDto);
        assessmentBooking = assessmentBookingRepository.save(assessmentBooking);
        return AssessmentBookingMapper.toDto(assessmentBooking);
    }

    @Override
    public AssessmentBookingDto getAssessmentBookingById(Integer bookingId) {
        AssessmentBooking assessmentBooking = assessmentBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment Booking not found with id: " + bookingId));
        return AssessmentBookingMapper.toDto(assessmentBooking);
    }

    @Override
    public List<AssessmentBookingDto> getAllAssessmentBookings() {
        List<AssessmentBooking> assessmentBookings = assessmentBookingRepository.findAll();
        return assessmentBookings.stream().map(AssessmentBookingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AssessmentBookingDto updateAssessmentBooking(Integer bookingId, AssessmentBookingDto assessmentBookingDto) {
        AssessmentBooking assessmentBooking = assessmentBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment Booking not found with id: " + bookingId));
        assessmentBooking.setTotalPrice(assessmentBookingDto.getTotalPrice());
        assessmentBooking.setSampleReturnDate(assessmentBookingDto.getSampleReturnDate());
        assessmentBooking.setStatus(assessmentBookingDto.getStatus());
        assessmentBooking.setPaymentStatus(assessmentBookingDto.getPaymentStatus());
        assessmentBooking.setPaymentType(assessmentBookingDto.getPaymentType());
        assessmentBooking.setPhone(assessmentBookingDto.getPhone());
        assessmentBooking.setDateCreated(assessmentBookingDto.getDateCreated());
        assessmentBooking.setFeedback(assessmentBookingDto.getFeedback());
        assessmentBooking.setQuantities(assessmentBookingDto.getQuantities());
        assessmentBooking = assessmentBookingRepository.save(assessmentBooking);
        return AssessmentBookingMapper.toDto(assessmentBooking);
    }

    @Override
    public void deleteAssessmentBooking(Integer bookingId) {
        if (!assessmentBookingRepository.existsById(bookingId)) {
            throw new ResourceNotFoundException("Assessment Booking not found with id: " + bookingId);
        }
        assessmentBookingRepository.deleteById(bookingId);
    }

    @Override
    public AssessmentBookingDto changeStatus(Integer bookingId, Integer status) {
        AssessmentBooking assessmentBooking = assessmentBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment Booking not found with id: " + bookingId));
        assessmentBooking.setStatus(status);
        assessmentBooking = assessmentBookingRepository.save(assessmentBooking);
        return AssessmentBookingMapper.toDto(assessmentBooking);
    }
}
