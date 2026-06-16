package com.ltr.module;

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
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String mainCategory;
    @Column(nullable = false)
    private String subCategory;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private String itemType;
    @Column(nullable = false)
    private Double price;
    private String description;
    private String imageName;
    private String imageType;
    @Lob
    @Column(nullable = false)
    private byte[] image;
}
