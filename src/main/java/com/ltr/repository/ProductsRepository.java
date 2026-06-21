package com.ltr.repository;

import com.ltr.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
