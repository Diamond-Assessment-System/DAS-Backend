package com.project.DASBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime dateCreated;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Clarity", nullable = false)
    private String clarity;

    @ManyToOne
    @JoinColumn(name = "Sample_Id", nullable = false)
    private BookingSample bookingSample;
}
