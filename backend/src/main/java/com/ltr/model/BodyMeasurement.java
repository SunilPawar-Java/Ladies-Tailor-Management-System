package com.ltr.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BodyMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;           //   M, S, X, XS, XL, XXl, etc.
    private Double upperFullLength;     //   Height
    private Double bust;           // Chest
    private Double waist;          //
    private Double shoulder;
    private Double sleeveLength;
    private Double sleeveRound;
    private Double armholeRound;
    private Double frontNeckDepth;
    private Double backNeckDepth;

    // Bottom
    private Double bottomFullLength;
    private Double hip;
    private Double rise;
    private Double thigh;
    private Double knee;
    private Double calf;
    private Double ankle;
    private Double instep;
    private String message;            //  more details


    @OneToOne(mappedBy = "bodyMeasurement")
    @JsonBackReference("orders-body_measurement")
    private Orders order;
}
