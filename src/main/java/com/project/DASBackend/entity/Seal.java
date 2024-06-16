package com.project.DASBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Seal")
@Data
@Builder
public class Seal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SealId")
    private Integer sealId;

    @Column(name = "Shape", nullable = false)
    private String shape;

    @Column(name = "Weight", nullable = false)
    private Float weight;

    @Column(name = "Size", nullable = false)
    private Float size;

    @Column(name = "DateCreated", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Clarity", nullable = false)
    private String clarity;
}
