package com.project.DASBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Commitment_Paper")
@Data
@Builder
public class CommitmentPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Commitment_Id")
    private Integer commitmentId;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Date_Created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "Approval_Date", nullable = false)
    private LocalDate approvalDate;

    @Column(name = "CommitmentType", nullable = false)
    private String commitmentType;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "Booking_Id", nullable = false)
    private AssessmentBooking assessmentBooking;

    @ManyToOne
    @JoinColumn(name = "Account_Id", nullable = false)
    private Account account;
}
