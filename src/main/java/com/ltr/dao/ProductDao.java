package com.ltr.dao;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {

    private Long id;
    private String mainCategory;
    private String subCategory;
    private String itemType;
    private String itemName;
    private Double price;
    private String description;
    private MultipartFile image;
}
