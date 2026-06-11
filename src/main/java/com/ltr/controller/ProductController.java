package com.ltr.controller;

import com.ltr.dao.product.ProductDao;
import com.ltr.entity.products.Products;
import com.ltr.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@ModelAttribute ProductDao productDao) throws IOException {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("product", productsService.addProduct(productDao)));
    }

    @GetMapping("/all")
    public List<Products> getAllProduct(){
        return productsService.getAllProducts();
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getProductImageById(@PathVariable Long id){
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(productsService.getProductImageById(id));

    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id){
        return productsService.getProductById(id);
    }

    @GetMapping("/maincategory/{category}")
    public List<Products> getProductsByMainCategory(@PathVariable String category){
        return productsService.getProductsByMainCategory(category);
    }

    @GetMapping("/subcategory/{category}")
    public List<Products> getProductsBySubCategory(@PathVariable String category){
        return productsService.getProductsBySubCategory(category);
    }

    @GetMapping("/itemtype/{category}")
    public List<Products> getProductsByItemType(@PathVariable String category){
        return productsService.getProductsByItemType(category);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
        return productsService.deleteProductById(id);
    }

    @DeleteMapping("/delete/maincategory/{category}")
    public String deleteProductsByMainCategory(@PathVariable String category){
        return productsService.deleteAllByMainCategory(category);
    }

    @DeleteMapping("/delete/subcategory/{category}")
    public String deleteProductsBySubCategory(@PathVariable String category){
        return productsService.deleteAllBySubCategory(category);
    }

    @DeleteMapping("/delete/itemtype/{itemType}")
    public String deleteProductsByItemType(@PathVariable String itemType){
        return productsService.deleteAllByItemType(itemType);
    }


}
