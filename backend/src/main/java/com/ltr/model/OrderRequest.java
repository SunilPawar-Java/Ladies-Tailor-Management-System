package com.ltr.model;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long productId;
    private Double totalAmount;

    private String clothMaterial;    // Clothing Material Type - Silk, Cotton, linen, wool, etc.
    private String clothColor;
    private String frontNeck;
    private String backNeck;
    private String fitting;
    private String sleeve;
    private String openingClosing;

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
    private String message;

}
