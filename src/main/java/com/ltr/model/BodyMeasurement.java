package com.ltr.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BodyMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Column(nullable = false)
    private String materialType;
    private String size;
    private Double fullLength;
    private Double bust;
    private Double waist;
    private Double shoulder;
    private Double sleeveLength;
    private Double sleeveRound;
    private Double armholeRound;
    private Double frontNeckDepth;
    private Double backNeckDepth;
    private String note;

    // Bottom
    private Double Hips;
    private Double rise;
    private Double thigh;
    private Double knee;
    private Double calf;
    private Double ankle;
    private Double instep;

    @OneToOne(mappedBy = "bodyMeasurement")
    @JsonBackReference("orders-body_measurement")
    private Orders order;
}
