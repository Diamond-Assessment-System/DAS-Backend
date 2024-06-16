package com.project.DASBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssessmentBookingDto {

    private Integer bookingId;

    @NotNull(message = "Total price cannot be null")
    private Float totalPrice;

    @NotNull(message = "Sample return date cannot be null")
    private LocalDate sampleReturnDate;

    @NotNull(message = "Status cannot be null")
    private Integer status;

    @NotNull(message = "Payment status cannot be null")
    private Integer paymentStatus;

    @NotNull(message = "Payment type cannot be null")
    private Integer paymentType;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @NotNull(message = "Date created cannot be null")
    private LocalDate dateCreated;

    private String feedback;

    @NotNull(message = "Quantities cannot be null")
    private Integer quantities;

    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;

    @NotNull(message = "Service ID cannot be null")
    private Integer serviceId;
}
