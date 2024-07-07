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

//    @NotNull(message = "Sample return date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime sampleReturnDate;

//    @NotNull(message = "Status cannot be null")
    private Integer status;

//    @NotNull(message = "Payment status cannot be null")
    private Integer paymentStatus;

//    @NotNull(message = "Payment type cannot be null")
    private Integer paymentType;

//    @NotBlank(message = "Phone cannot be blank")
    private String phone;

//    @NotNull(message = "Date created cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime dateCreated;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime dateReceived;

    private String feedback;

//    @NotNull(message = "Quantities cannot be null")
    private Integer quantities;

//    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;

//    @NotNull(message = "Service ID cannot be null")
    private Integer serviceId;

    private Integer consultingAccountId;
}
