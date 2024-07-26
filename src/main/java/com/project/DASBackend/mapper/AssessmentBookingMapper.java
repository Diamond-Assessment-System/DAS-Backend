package com.project.DASBackend.mapper;

import com.project.DASBackend.dto.AssessmentBookingDto;
import com.project.DASBackend.entity.AssessmentBooking;

public class AssessmentBookingMapper {
    public static AssessmentBookingDto toDto(AssessmentBooking assessmentBooking) {
        if (assessmentBooking == null) {
            return null;
        }
        return AssessmentBookingDto.builder()
                .bookingId(assessmentBooking.getBookingId())
                .totalPrice(assessmentBooking.getTotalPrice())
                .sampleReturnDate(assessmentBooking.getSampleReturnDate())
                .status(assessmentBooking.getStatus())
                .paymentStatus(assessmentBooking.getPaymentStatus())
                .paymentType(assessmentBooking.getPaymentType())
                .phone(assessmentBooking.getPhone())
                .dateCreated(assessmentBooking.getDateCreated())
                .dateReceived(assessmentBooking.getDateReceived())
                .feedback(assessmentBooking.getFeedback())
                .quantities(assessmentBooking.getQuantities())
                .note(assessmentBooking.getNote())
                .cancelReason(assessmentBooking.getCancelReason())
                .accountId(assessmentBooking.getAccount().getAccountId())
                .serviceId(assessmentBooking.getService().getServiceId())
                .serviceId(assessmentBooking.getService().getServiceId())
                .consultingAccountId(assessmentBooking.getConsultingAccount() != null ? assessmentBooking.getConsultingAccount().getAccountId() : null)
                .build();
    }

    public static AssessmentBooking toEntity(AssessmentBookingDto assessmentBookingDto) {
        if (assessmentBookingDto == null) {
            return null;
        }
        AssessmentBooking assessmentBooking = new AssessmentBooking();
        assessmentBooking.setBookingId(assessmentBookingDto.getBookingId());
        assessmentBooking.setTotalPrice(assessmentBookingDto.getTotalPrice());
        assessmentBooking.setSampleReturnDate(assessmentBookingDto.getSampleReturnDate());
        assessmentBooking.setStatus(assessmentBookingDto.getStatus());
        assessmentBooking.setPaymentStatus(assessmentBookingDto.getPaymentStatus());
        assessmentBooking.setPaymentType(assessmentBookingDto.getPaymentType());
        assessmentBooking.setPhone(assessmentBookingDto.getPhone());
        assessmentBooking.setNote(assessmentBookingDto.getNote());
        assessmentBooking.setDateCreated(assessmentBookingDto.getDateCreated());
        assessmentBooking.setFeedback(assessmentBookingDto.getFeedback());
        assessmentBooking.setQuantities(assessmentBookingDto.getQuantities());
        assessmentBooking.setDateReceived(assessmentBookingDto.getDateReceived());
        assessmentBooking.setCancelReason(assessmentBooking.getCancelReason());
        return assessmentBooking;
    }
}
