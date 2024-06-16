package com.project.DASBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Service_price_list")
@Data
@Builder
public class ServicePriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Service_price_Id")
    private Integer servicePriceId;

    @Column(name = "Size_from", nullable = false)
    private Float sizeFrom;

    @Column(name = "Size_to", nullable = false)
    private Float sizeTo;

    @Column(name = "Init_price", nullable = false)
    private Float initPrice;

    @Column(name = "Price_unit", nullable = false)
    private Float priceUnit;


}
