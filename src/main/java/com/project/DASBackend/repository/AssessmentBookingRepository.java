package com.project.DASBackend.repository;

import com.project.DASBackend.entity.AssessmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentBookingRepository extends JpaRepository<AssessmentBooking, Integer> {

    @Query("SELECT ab FROM AssessmentBooking ab WHERE ab.consultingAccount = :consultingAccountId ORDER BY ab.status ASC, ab.dateReceived DESC")
    List<AssessmentBooking> findByConsultingAccountIdOrdered(@Param("consultingAccountId") Integer consultingAccountId);

    @Query("SELECT ab FROM AssessmentBooking ab WHERE ab.account = :accountId ORDER BY ab.status ASC, ab.dateReceived DESC")
    List<AssessmentBooking> findByAccountIdOrdered(@Param("accountId") Integer accountId);

    @Query("SELECT ab FROM AssessmentBooking ab ORDER BY ab.status ASC, ab.dateReceived DESC")
    List<AssessmentBooking> findAllOrdered();
}
