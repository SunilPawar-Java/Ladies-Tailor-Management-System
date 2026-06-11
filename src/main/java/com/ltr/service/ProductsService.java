package com.ltr.service;

import com.ltr.dao.product.ProductDao;
import com.ltr.entity.products.Products;
import com.ltr.exception.classes.ProductNotFoundException;
import com.ltr.repository.procuct.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductsService {
    byte[] b = new byte[0];
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products addProduct(ProductDao productDao) throws IOException {
        Products product = new Products();
        product.setMainCategory(productDao.getMainCategory());
        product.setSubCategory(productDao.getSubCategory());
        product.setItemType(productDao.getItemType());
        product.setName(productDao.getName());
        product.setPrice(productDao.getPrice());
        product.setDescription(productDao.getDescription());
        product.setImageName(productDao.getImage().getOriginalFilename());
        product.setImageType(productDao.getImage().getContentType());
        product.setImage(productDao.getImage().getBytes());
        return productsRepository.save(product);
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll()
                .stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    public Products getProductById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product couldn't find for id "+id));
    }

    public byte[] getProductImageById(Long id){
        return productsRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("image not found for id "+id)).getImage();
    }

    @Transactional
    public List<Products> getProductsByMainCategory(String category){
        return productsRepository.findAllByMainCategory(category)
                .stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    @Transactional
    public List<Products> getProductsBySubCategory(String category){
        return productsRepository.findAllBySubCategory(category).stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    @Transactional
    public List<Products> getProductsByItemType(String itemType){
        return productsRepository.findAllByItemType(itemType)
                .stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    @Transactional
    public String deleteProductById(Long id){
        productsRepository.delete(getProductById(id));
        return "Product delete successfully for id "+id;
    }

    @Transactional
    public String deleteAllByMainCategory(String category){
        productsRepository.deleteAll(getProductsByMainCategory(category));
        return "All products successfully deleted for category "+category;
    }

    @Transactional
    public String deleteAllBySubCategory(String category){
        productsRepository.deleteAll(getProductsBySubCategory(category));
        return "All products successfully deleted for category "+category;
    }

    @Transactional
    public String deleteAllByItemType(String itemType){
        productsRepository.deleteAll(getProductsBySubCategory(itemType));
        return "All products successfully deleted for category "+itemType;
    }



}
