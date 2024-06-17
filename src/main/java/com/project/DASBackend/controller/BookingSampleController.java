package com.project.DASBackend.controller;

import com.project.DASBackend.dto.BookingSampleDto;
import com.project.DASBackend.service.BookingSampleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/booking-samples")
public class BookingSampleController {

    @Autowired
    private BookingSampleService bookingSampleService;

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

    @PatchMapping("{id}/status")
    public ResponseEntity<BookingSampleDto> changeStatus(@PathVariable("id") Integer sampleId, @RequestParam("status") Integer status) {
        BookingSampleDto updatedSample = bookingSampleService.changeStatus(sampleId, status);
        return ResponseEntity.ok(updatedSample);
    }
}
