package com.project.DASBackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssessmentBookingDto {

    private Integer bookingId;

    private Float totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime sampleReturnDate;

    private Integer status;

    private Integer paymentStatus;

    private Integer paymentType;

    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime dateCreated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime dateReceived;

    private String feedback;

    private Integer quantities;

    private Integer accountId;

    private Integer serviceId;

    private Integer consultingAccountId;

    private String cancelReason;
}
