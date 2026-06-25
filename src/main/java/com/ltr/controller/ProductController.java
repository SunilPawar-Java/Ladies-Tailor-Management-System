package com.ltr.controller;
import com.ltr.dao.ProductDao;
import com.ltr.model.Products;
import com.ltr.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@ModelAttribute ProductDao productDao) throws IOException {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("message", productsService.addProduct(productDao)));
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

    @GetMapping("/get/{id}")
    public Products getProductById(@PathVariable Long id){
        return productsService.getProductById(id);
    }

    @GetMapping("/maincategory/{category}")
    public List<ProductDao> getProductsByMainCategory(@PathVariable String category){
        return productsService.getProductsByMainCategory(category);
    }

    @GetMapping("/subcategory/{category}")
    public List<ProductDao> getProductsBySubCategory(@PathVariable String category){
        return productsService.getProductsBySubCategory(category);
    }

    @GetMapping("/itemtype/{category}")
    public List<ProductDao> getProductsByItemType(@PathVariable String category){
        return productsService.getProductsByItemType(category);
    }

    @GetMapping("/itemname/{category}")
    public List<ProductDao> getProductsByItemName(@PathVariable String category){
        return productsService.getProductsByItemName(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProductPartially(@PathVariable Long id, @ModelAttribute ProductDao productDao) throws IOException {
        return ResponseEntity.ok().body(Map.of("message", productsService.updateProductPartially(id, productDao)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public String updateProductFully(@PathVariable Long id, @ModelAttribute ProductDao productDao) throws IOException {
        return productsService.updateProductFully(id, productDao);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
        return productsService.deleteProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/maincategory/{category}")
    public String deleteProductsByMainCategory(@PathVariable String category){
        return productsService.deleteAllByMainCategory(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/subcategory/{category}")
    public String deleteProductsBySubCategory(@PathVariable String category){
        return productsService.deleteAllBySubCategory(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/itemtype/{itemType}")
    public String deleteProductsByItemType(@PathVariable String itemType){
        return productsService.deleteAllByItemType(itemType);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/itemname/{itemName}")
    public String deleteProductsByItemName(@PathVariable String itemName){
        return productsService.deleteAllByItemName(itemName);
    }
}
