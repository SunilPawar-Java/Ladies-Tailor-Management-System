package com.ltr.entity.orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BodyMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
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
}
