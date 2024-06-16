package com.project.DASBackend.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

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
    private LocalDate dateCreated;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @NotBlank(message = "Clarity cannot be blank")
    private String clarity;
}
