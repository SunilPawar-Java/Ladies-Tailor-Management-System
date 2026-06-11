package com.ltr.dao.product;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {

    @NonNull
    private String mainCategory;
    @NonNull
    private String subCategory;
    @NonNull
    private String itemType;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    private String description;
    @NonNull
    private MultipartFile image;
}
