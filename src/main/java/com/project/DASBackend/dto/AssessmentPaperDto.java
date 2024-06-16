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
public class AssessmentPaperDto {

    private Integer diamondId;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotNull(message = "Size cannot be null")
    private Float size;

    @NotBlank(message = "Shape cannot be blank")
    private String shape;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @NotBlank(message = "Clarity cannot be blank")
    private String clarity;

    @NotBlank(message = "Polish cannot be blank")
    private String polish;

    @NotBlank(message = "Symmetry cannot be blank")
    private String symmetry;

    @NotBlank(message = "Fluorescence cannot be blank")
    private String fluorescence;

    @NotNull(message = "Weight cannot be null")
    private Float weight;

    private String comments;

    @NotNull(message = "Date created cannot be null")
    private LocalDate dateCreated;

    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;

    @NotNull(message = "Sample ID cannot be null")
    private Integer sampleId;
}
