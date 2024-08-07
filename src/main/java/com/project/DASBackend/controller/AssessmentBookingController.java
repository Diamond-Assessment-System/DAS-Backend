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
    public ResponseEntity<AssessmentBookingDto> createAssessmentBooking(@RequestBody AssessmentBookingDto assessmentBookingDto) {
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

    @PutMapping("proceed/{id}")
    public ResponseEntity<AssessmentBookingDto> proceedAssessmentBooking(@RequestBody AssessmentBookingDto assessmentBookingDto,
                                                                        @PathVariable("id") Integer bookingId) {
        return ResponseEntity.ok(assessmentBookingService.proceedAssessmentBooking(bookingId, assessmentBookingDto));
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

    @PutMapping("{id}/status/{status}")
    public ResponseEntity<AssessmentBookingDto> changeStatus(@PathVariable("id") Integer bookingId
                                                            , @PathVariable("status") Integer status) {
        AssessmentBookingDto updatedBooking = assessmentBookingService.changeStatus(bookingId, status);
        return ResponseEntity.ok(updatedBooking);
    }
    
    @PutMapping("{id}/feedback")
    public ResponseEntity<AssessmentBookingDto> feedback(@PathVariable("id") Integer bookingId, @RequestBody String feedback) {
        AssessmentBookingDto updatedBooking = assessmentBookingService.feedback(bookingId, feedback);
        return ResponseEntity.ok(updatedBooking);
    }

    @PatchMapping("{bookingId}/assign/{staffId}")
    public ResponseEntity<AssessmentBookingDto> assignAssessmentBooking(@PathVariable("bookingId") Integer bookingId, @PathVariable("staffId") Integer staffId) {
        AssessmentBookingDto updatedBooking = assessmentBookingService.assignStaff(bookingId, staffId);
        return ResponseEntity.ok(updatedBooking);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<AssessmentBookingDto>> findByAccountId(@PathVariable("accountId") Integer accountId) {
        List<AssessmentBookingDto> assessmentBookingDtos = assessmentBookingService.findByAccountIdOrdered(accountId);
        return ResponseEntity.ok(assessmentBookingDtos);
    }


    @GetMapping("/ordered")
    public ResponseEntity<List<AssessmentBookingDto>> findAllOrdered() {
        List<AssessmentBookingDto> assessmentBookingDtos = assessmentBookingService.findAllOrdered();
        return ResponseEntity.ok(assessmentBookingDtos);
    }

}
