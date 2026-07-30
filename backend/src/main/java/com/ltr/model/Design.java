package com.ltr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clothMaterial;    // Clothing Material Type - Silk, Cotton, linen, wool, etc.
    private String clothColor;
    private String frontNeck;
    private String backNeck;
    private String fitting;
    private String sleeve;
    private String openingClosing;

    @OneToOne(mappedBy = "design")
    @JsonBackReference("orders-design")
    private Orders order;
}
