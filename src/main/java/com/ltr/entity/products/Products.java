package com.ltr.entity.products;

import jakarta.annotation.Nonnull;
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
    @Nonnull
    private String mainCategory;
    @Nonnull
    private String subCategory;
    @Nonnull
    private String itemName;
    @Nonnull
    private String itemType;
    @Nonnull
    private Double price;
    private String description;
    private String imageName;
    private String imageType;
    @Lob
    @Nonnull
    private byte[] image;
}
