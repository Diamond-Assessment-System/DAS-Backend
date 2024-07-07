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
public class CommitmentPaperDto {

    private Integer commitmentId;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Date created cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime dateCreated;

    @NotNull(message = "Approval date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime approvalDate;

    @NotBlank(message = "Commitment type cannot be blank")
    private String commitmentType;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Status cannot be null")
    private Integer status;

    @NotNull(message = "Booking ID cannot be null")
    private Integer bookingId;

    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;
}
