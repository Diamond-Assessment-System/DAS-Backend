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
public class ServiceDto {

    private Integer serviceId;

    @NotBlank(message = "Service name cannot be blank")
    private String serviceName;

    @NotBlank(message = "Service description cannot be blank")
    private String serviceDescription;

    @NotNull(message = "Service status cannot be null")
    private Integer serviceStatus;

    private Integer serviceType;

    @NotNull(message = "Service price cannot be null")
    private Float servicePrice;

    @NotNull(message = "Service time cannot be null")
    private Integer serviceTime;
}
