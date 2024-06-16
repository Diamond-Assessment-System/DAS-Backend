package com.project.DASBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Assessment_Booking")
@Data
@Builder
public class AssessmentBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Booking_Id")
    private Integer bookingId;

    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    @Column(name = "Sample_Return_Date", nullable = false)
    private LocalDate sampleReturnDate;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "Payment_status", nullable = false)
    private Integer paymentStatus;

    @Column(name = "Payment_type", nullable = false)
    private Integer paymentType;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "Feedback")
    private String feedback;

    @Column(name = "Quantities", nullable = false)
    private Integer quantities;

    @ManyToOne
    @JoinColumn(name = "Account_Id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "Service_Id", nullable = false)
    private Services service;
}
