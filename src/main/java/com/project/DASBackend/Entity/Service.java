package com.project.DASBackend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Service")
@Data
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Service_Id")
    private int serviceId;

    @Column(name = "Service_name", nullable = false)
    private String serviceName;

    @Column(name = "Service_description", nullable = false)
    private String serviceDescription;

    @Column(name = "Service_price", nullable = false)
    private int servicePrice;

    @Column(name = "Service_status", nullable = false)
    private int serviceStatus;
}
