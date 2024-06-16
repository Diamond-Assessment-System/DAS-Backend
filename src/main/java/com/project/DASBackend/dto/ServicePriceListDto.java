package com.project.DASBackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ServicePriceListDto {

    private Integer servicePriceId;

    @NotNull(message = "Size from cannot be null")
    private Float sizeFrom;

    @NotNull(message = "Size to cannot be null")
    private Float sizeTo;

    @NotNull(message = "Init price cannot be null")
    private Float initPrice;

    @NotNull(message = "Price unit cannot be null")
    private Float priceUnit;

}
