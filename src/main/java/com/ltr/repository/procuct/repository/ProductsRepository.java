package com.ltr.repository.procuct.repository;

import com.ltr.entity.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByMainCategory(String category);

    List<Products> findAllByItemType(String itemType);

    List<Products> findAllBySubCategory(String subCategory);

    List<Products> findAllByItemName(String itemName);

    void deleteAllByMainCategory(String mainCategory);

    void deleteAllBySubCategory(String subCategory);

    void deleteAllByItemType(String itemType);

}
