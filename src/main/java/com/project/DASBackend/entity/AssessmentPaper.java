package com.project.DASBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Assessment_paper")
@Data
@Builder
public class AssessmentPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Diamond_Id")
    private Integer diamondId;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "Size", nullable = false)
    private Float size;

    @Column(name = "Shape", nullable = false)
    private String shape;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Clarity", nullable = false)
    private String clarity;

    @Column(name = "Polish", nullable = false)
    private String polish;

    @Column(name = "Symmetry", nullable = false)
    private String symmetry;

    @Column(name = "Fluorescence", nullable = false)
    private String fluorescence;

    @Column(name = "Weight", nullable = false)
    private Float weight;

    @Column(name = "Comments")
    private String comments;

    @Column(name = "Date_Created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "Paper_Image", nullable = false)
    private String paperImage;

    @ManyToOne
    @JoinColumn(name = "Account_Id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "Sample_Id", nullable = false)
    private BookingSample bookingSample;
}
