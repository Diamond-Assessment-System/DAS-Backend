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

    private Integer status;

    private Integer isDiamond;

    private String name;

    private Float size;

    private Float price;

    private Integer bookingId;

    private Integer accountId;

//    private Integer servicePriceId;

}
