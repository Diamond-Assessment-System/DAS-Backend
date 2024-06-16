package com.project.DASBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull(message = "IsDiamond cannot be null")
    private Integer isDiamond;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Size cannot be null")
    private Float size;

    @NotNull(message = "Price cannot be null")
    private Float price;

    @NotNull(message = "Booking ID cannot be null")
    private Integer bookingId;

    @NotNull(message = "Account ID cannot be null")
    private Integer accountId;

    @NotNull(message = "Service price ID cannot be null")
    private Integer servicePriceId;
}
