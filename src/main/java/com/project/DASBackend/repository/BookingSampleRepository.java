package com.project.DASBackend.repository;

import com.project.DASBackend.entity.BookingSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingSampleRepository extends JpaRepository<BookingSample, Integer> {
    List<BookingSample> findAllByAssessmentBooking_BookingId(Integer bookingId);

    List<BookingSample> findAllByAccount_AccountIdOrderByStatusAsc(Integer accountId);

    long countByAssessmentBooking_BookingId(Integer bookingId);

    long countByAssessmentBooking_BookingIdAndStatusIn(Integer bookingId, List<Integer> statuses);

    long countByAssessmentBooking_BookingIdAndStatus(Integer bookingId, Integer status);
}
