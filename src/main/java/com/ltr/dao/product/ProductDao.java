package com.ltr.dao.product;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {

    private String mainCategory;
    private String subCategory;
    private String itemType;
    private String itemName;
    private Double price;
    private String description;
    private MultipartFile image;
}
