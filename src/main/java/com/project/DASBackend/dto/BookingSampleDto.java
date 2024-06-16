package com.project.DASBackend.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingSampleDto {

    private Integer sampleId;

    @NotNull(message = "Status cannot be null")
    private Integer status;

    @NotNull(message = "Is diamond cannot be null")
    private Integer isDiamond;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Size cannot be null")
    private Float size;

    @NotNull(message = "Price cannot be null")
    private Float price;

    @NotNull(message = "Diamond id cannot be null")
    private Integer diamondId;

    @NotNull(message = "Booking id cannot be null")
    private Integer bookingId;

    @NotNull(message = "Account id cannot be null")
    private Integer accountId;

    @NotNull(message = "Seal id cannot be null")
    private Integer sealId;
}
