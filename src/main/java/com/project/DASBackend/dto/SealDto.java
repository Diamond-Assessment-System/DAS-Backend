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
public class SealDto {

    private Integer sealId;

    @NotBlank(message = "Shape cannot be blank")
    private String shape;

    @NotNull(message = "Weight cannot be null")
    private Float weight;

    @NotNull(message = "Size cannot be null")
    private Float size;

    @NotNull(message = "Date created cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd - HH:mm:ss")
    private LocalDateTime dateCreated;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @NotBlank(message = "Clarity cannot be blank")
    private String clarity;

    @NotNull(message = "Sample ID cannot be null")
    private Integer sampleId;
}
