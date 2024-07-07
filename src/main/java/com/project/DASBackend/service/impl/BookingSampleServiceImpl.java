package com.project.DASBackend.service.impl;

import com.project.DASBackend.dto.BookingSampleDto;
import com.project.DASBackend.entity.Account;
import com.project.DASBackend.entity.AssessmentBooking;
import com.project.DASBackend.entity.BookingSample;
import com.project.DASBackend.entity.ServicePriceList;
import com.project.DASBackend.exception.ResourceNotFoundException;
import com.project.DASBackend.mapper.BookingSampleMapper;
import com.project.DASBackend.repository.AccountRepository;
import com.project.DASBackend.repository.AssessmentBookingRepository;
import com.project.DASBackend.repository.BookingSampleRepository;
import com.project.DASBackend.repository.ServicePriceListRepository;
import com.project.DASBackend.service.BookingSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingSampleServiceImpl implements BookingSampleService {


    @Autowired
    private BookingSampleRepository bookingSampleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AssessmentBookingRepository assessmentBookingRepository;
    @Autowired
    private ServicePriceListRepository servicePriceListRepository;

    @Override
    public BookingSampleDto createBookingSample(BookingSampleDto bookingSampleDto) {
        BookingSample bookingSample = BookingSampleMapper.toEntity(bookingSampleDto);

        Account account = accountRepository.findById(bookingSampleDto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + bookingSampleDto.getAccountId()));
        bookingSample.setAccount(account);

        AssessmentBooking assessmentBooking = assessmentBookingRepository.findById(bookingSampleDto.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException("Assessment Booking not found with id: " + bookingSampleDto.getBookingId()));
        bookingSample.setAssessmentBooking(assessmentBooking);

//        ServicePriceList servicePriceList = servicePriceListRepository.findById(bookingSampleDto.getServicePriceId())
//                .orElseThrow(() -> new ResourceNotFoundException("Service Price List not found with id: " + bookingSampleDto.getServicePriceId()));
//        bookingSample.setServicePriceList(servicePriceList);

        bookingSample = bookingSampleRepository.save(bookingSample);
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public List<BookingSampleDto> createBookingSamples(List<BookingSampleDto> bookingSampleDtos) {
        List<BookingSample> bookingSamples = bookingSampleDtos.stream()
                .map(dto -> {
                    BookingSample entity = BookingSampleMapper.toEntity(dto);

                    // Assuming dto.getBookingId() returns the ID of the AssessmentBooking
                    AssessmentBooking booking = assessmentBookingRepository.findById(dto.getBookingId())
                            .orElseThrow(() -> new IllegalArgumentException("AssessmentBooking not found"));

                    entity.setAssessmentBooking(booking);
                    return entity;
                })
                .collect(Collectors.toList());

        // Assuming bookingSampleRepository.saveAll returns a List<BookingSample>
        bookingSamples = bookingSampleRepository.saveAll(bookingSamples);

        // Map the saved entities back to DTOs
        return bookingSamples.stream()
                .map(BookingSampleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingSampleDto getBookingSampleById(Integer sampleId) {
        BookingSample bookingSample = bookingSampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Sample not found with id: " + sampleId));
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public List<BookingSampleDto> getAllBookingSamples() {
        List<BookingSample> bookingSamples = bookingSampleRepository.findAll();
        return bookingSamples.stream().map(BookingSampleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingSampleDto> getBookingSamplesByBookingId(Integer bookingId) {
        List<BookingSample> bookingSamples = bookingSampleRepository.findAllByAssessmentBooking_BookingId(bookingId);
        return bookingSamples.stream()
                .sorted(Comparator.comparing(BookingSample::getStatus)) // Sorting by status in ascending order
                .map(BookingSampleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingSampleDto updateBookingSample(Integer sampleId, BookingSampleDto bookingSampleDto) {
        BookingSample bookingSample = bookingSampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Sample not found with id: " + sampleId));
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
        if (!bookingSampleRepository.existsById(sampleId)) {
            throw new ResourceNotFoundException("Booking Sample not found with id: " + sampleId);
        }
        bookingSampleRepository.deleteById(sampleId);
    }

    @Override
    public BookingSampleDto changeStatus(Integer sampleId, Integer status) {
        BookingSample bookingSample = bookingSampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Sample not found with id: " + sampleId));
        bookingSample.setStatus(status);
        bookingSample = bookingSampleRepository.save(bookingSample);
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public BookingSampleDto assignStaff(Integer sampleId, Integer assessmentAccountId) {
        BookingSample bookingSample = bookingSampleRepository.findById(sampleId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Sample not found with id: " + sampleId));
        Account account = accountRepository.findById(assessmentAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + assessmentAccountId));
        bookingSample.setAccount(account);
        bookingSample.setStatus(2);
        bookingSample = bookingSampleRepository.save(bookingSample);
        return BookingSampleMapper.toDto(bookingSample);
    }

    @Override
    public List<BookingSampleDto> getBookingSamplesByAssessmentAccountId(Integer assessmentAccountId) {
        List<BookingSample> bookingSamples = bookingSampleRepository.findAllByAccount_AccountIdOrderByStatusAsc(assessmentAccountId);
        return bookingSamples.stream().map(BookingSampleMapper::toDto).collect(Collectors.toList());
    }

    public long countAllBookingSamplesByBookingId(Integer bookingId) {
        return bookingSampleRepository.countByAssessmentBooking_BookingId(bookingId);
    }

    public long countBookingSamplesByBookingIdWithStatus1or2(Integer bookingId) {
        return bookingSampleRepository.countByAssessmentBooking_BookingIdAndStatusIn(bookingId, Arrays.asList(1, 2));
    }

    @Override
    public long countBookingSamplesByBookingIdWithStatus4(Integer bookingId) {
        return bookingSampleRepository.countByAssessmentBooking_BookingIdAndStatus(bookingId, 4);
    }
}
