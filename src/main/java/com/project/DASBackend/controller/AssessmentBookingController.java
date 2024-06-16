package com.project.DASBackend.controller;

import com.project.DASBackend.dto.AssessmentBookingDto;
import com.project.DASBackend.service.AssessmentBookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/assessment-bookings")
public class AssessmentBookingController {

    @Autowired
    private AssessmentBookingService assessmentBookingService;

    @PostMapping
    public ResponseEntity<AssessmentBookingDto> createAssessmentBooking(@Valid @RequestBody AssessmentBookingDto assessmentBookingDto) {
        return new ResponseEntity<>(assessmentBookingService.createAssessmentBooking(assessmentBookingDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssessmentBookingDto> getAssessmentBookingById(@PathVariable("id") Integer bookingId) {
        AssessmentBookingDto assessmentBookingDto = assessmentBookingService.getAssessmentBookingById(bookingId);
        return ResponseEntity.ok(assessmentBookingDto);
    }

    @GetMapping
    public ResponseEntity<List<AssessmentBookingDto>> getAllAssessmentBookings() {
        List<AssessmentBookingDto> assessmentBookingDtos = assessmentBookingService.getAllAssessmentBookings();
        return ResponseEntity.ok(assessmentBookingDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<AssessmentBookingDto> updateAssessmentBooking(@Valid @RequestBody AssessmentBookingDto assessmentBookingDto,
                                                                        @PathVariable("id") Integer bookingId) {
        return ResponseEntity.ok(assessmentBookingService.updateAssessmentBooking(bookingId, assessmentBookingDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAssessmentBooking(@PathVariable("id") Integer bookingId) {
        assessmentBookingService.deleteAssessmentBooking(bookingId);
        return ResponseEntity.ok("Assessment Booking deleted successfully");
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<AssessmentBookingDto> changeStatus(@PathVariable("id") Integer bookingId, @RequestParam("status") Integer status) {
        AssessmentBookingDto updatedBooking = assessmentBookingService.changeStatus(bookingId, status);
        return ResponseEntity.ok(updatedBooking);
    }
}
