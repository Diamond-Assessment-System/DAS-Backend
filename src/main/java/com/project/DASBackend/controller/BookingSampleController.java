package com.project.DASBackend.controller;

import com.project.DASBackend.dto.AssessmentBookingDto;
import com.project.DASBackend.dto.BookingSampleDto;
import com.project.DASBackend.dto.ServiceDto;
import com.project.DASBackend.entity.Services;
import com.project.DASBackend.repository.AssessmentBookingRepository;
import com.project.DASBackend.service.AssessmentBookingService;
import com.project.DASBackend.service.BookingSampleService;
import com.project.DASBackend.service.ServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/booking-samples")
public class BookingSampleController {

    @Autowired
    private BookingSampleService bookingSampleService;
    @Autowired
    private AssessmentBookingService assessmentBookingService;
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/sample")
    public ResponseEntity<BookingSampleDto> createBookingSample(@RequestBody BookingSampleDto bookingSampleDto) {
        return new ResponseEntity<>(bookingSampleService.createBookingSample(bookingSampleDto), HttpStatus.CREATED);
    }

    @PostMapping("/samples")
    public ResponseEntity<List<BookingSampleDto>> createBookingSamples(@RequestBody List<BookingSampleDto> bookingSampleDtos) {
        List<BookingSampleDto> createdSamples = bookingSampleService.createBookingSamples(bookingSampleDtos);
        return new ResponseEntity<>(createdSamples, HttpStatus.CREATED);
    }



    @GetMapping("{id}")
    public ResponseEntity<BookingSampleDto> getBookingSampleById(@PathVariable("id") Integer sampleId) {
        BookingSampleDto bookingSampleDto = bookingSampleService.getBookingSampleById(sampleId);
        return ResponseEntity.ok(bookingSampleDto);
    }

    @PutMapping("{id}/assign/{staffId}")
    public ResponseEntity<BookingSampleDto> resetPrice(@PathVariable("id") Integer sampleId) {
        BookingSampleDto updatedSample = bookingSampleService.getBookingSampleById(sampleId);
        if (updatedSample.getStatus() == 3){
            List<ServiceDto> services = serviceService.getAllServices();
            AssessmentBookingDto assessmentBookingDto = assessmentBookingService.findBySampleId(sampleId);
            services.stream()
                    .filter(s -> Objects.equals(s.getServiceId(), assessmentBookingDto.getServiceId()))
                    .findFirst()
                    .ifPresent(service -> updatedSample.setPrice(service.getServicePrice()));
        }
        return ResponseEntity.ok(updatedSample);
    }

    @GetMapping
    public ResponseEntity<List<BookingSampleDto>> getAllBookingSamples() {
        List<BookingSampleDto> bookingSampleDtos = bookingSampleService.getAllBookingSamples();
        return ResponseEntity.ok(bookingSampleDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookingSampleDto> updateBookingSample(@RequestBody BookingSampleDto bookingSampleDto,
                                                                @PathVariable("id") Integer sampleId) {
        return ResponseEntity.ok(bookingSampleService.updateBookingSample(sampleId, bookingSampleDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookingSample(@PathVariable("id") Integer sampleId) {
        bookingSampleService.deleteBookingSample(sampleId);
        return ResponseEntity.ok("Booking Sample deleted successfully");
    }

    @PutMapping("{id}/status/{status}")
    public ResponseEntity<BookingSampleDto> changeStatus(@PathVariable("id") Integer sampleId, @PathVariable("status") Integer status) {
        BookingSampleDto updatedSample = bookingSampleService.changeStatus(sampleId, status);
        return ResponseEntity.ok(updatedSample);
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<BookingSampleDto> cancelBookingSample(@PathVariable("id") Integer sampleId, @RequestBody String cancelReason) {
        BookingSampleDto updatedSample = bookingSampleService.changeStatus(sampleId, 4, cancelReason);
        return ResponseEntity.ok(updatedSample);
    }

    @PutMapping("{sampleId}/assign/{staffId}")
    public ResponseEntity<BookingSampleDto> assignBookingSample(@PathVariable("sampleId") Integer sampleId, @PathVariable("staffId") Integer staffId) {
        BookingSampleDto updatedSample = bookingSampleService.assignStaff(sampleId, staffId);
        return ResponseEntity.ok(updatedSample);
    }

    @GetMapping("booking/{bookingId}")
    public ResponseEntity<List<BookingSampleDto>> getBookingSamplesByBookingId(@PathVariable("bookingId") Integer bookingId) {
        List<BookingSampleDto> bookingSampleDtos = bookingSampleService.getBookingSamplesByBookingId(bookingId);
        return ResponseEntity.ok(bookingSampleDtos);
    }

    @GetMapping("assessment-account/{assessmentAccountId}")
    public ResponseEntity<List<BookingSampleDto>> getBookingSamplesByAssessmentAccountId(@PathVariable("assessmentAccountId") Integer assessmentAccountId) {
        List<BookingSampleDto> bookingSampleDtos = bookingSampleService.getBookingSamplesByAssessmentAccountId(assessmentAccountId);
        return ResponseEntity.ok(bookingSampleDtos);
    }

    @GetMapping("/count")
    public long countAllBookingSamplesByBookingId(@RequestParam Integer bookingId) {
        return bookingSampleService.countAllBookingSamplesByBookingId(bookingId);
    }

    @GetMapping("/count-by-status")
    public long countBookingSamplesByBookingIdWithStatus1or2(@RequestParam Integer bookingId) {
        return bookingSampleService.countBookingSamplesByBookingIdWithStatus1or2(bookingId);
    }
    @GetMapping("/count-sealed")
    public  long countBookingSamplesByBookingIdWithStatus4(@RequestParam Integer bookingId) {
        return bookingSampleService.countBookingSamplesByBookingIdWithStatus4(bookingId);
    }

}
