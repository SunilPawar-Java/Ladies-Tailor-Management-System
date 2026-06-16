package com.ltr.service;

import com.ltr.dao.ProductDao;
import com.ltr.module.Products;
import com.ltr.exception.ProductNotFoundException;
import com.ltr.repository.ProductsRepository;
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

    public String addProduct(ProductDao productDao) throws IOException {
        Products product = new Products();
        product.setMainCategory(productDao.getMainCategory());
        product.setSubCategory(productDao.getSubCategory());
        product.setItemType(productDao.getItemType());
        product.setItemName(productDao.getItemName());
        product.setPrice(productDao.getPrice());
        product.setDescription(productDao.getDescription());
        product.setImageName(productDao.getImage().getOriginalFilename());
        product.setImageType(productDao.getImage().getContentType());
        product.setImage(productDao.getImage().getBytes());
        productsRepository.save(product);
        return "Product Added Successfully";
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll()
                .stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    public Products getProductById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("No Product Found for id "+id));
    }

    public byte[] getProductImageById(Long id){
        return productsRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Not Image Found for id "+id)).getImage();
    }

    @Transactional
    public List<Products> getProductsByMainCategory(String category){
        return productsRepository.findAllByMainCategory(category)
                .stream().peek(products -> products.setImage(b)).toList();
    }

    @Transactional
    public List<Products> getProductsBySubCategory(String category){
        return productsRepository.findAllBySubCategory(category).stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    public List<Products> getProductsByItemName(String itemName){
        return productsRepository.findAllByItemName(itemName)
                .stream().map(products -> {products.setImage(b); return products; }).toList();

    }

    @Transactional
    public List<Products> getProductsByItemType(String itemType){
        return productsRepository.findAllByItemType(itemType)
                .stream().map(products -> {products.setImage(b); return products; }).toList();
    }

    public String updateProductPartially(Long id, ProductDao productDao) throws IOException {
        Products product = getProductById(id);
        if(productDao.getMainCategory() != null)
            product.setMainCategory(productDao.getMainCategory());
        if (productDao.getSubCategory() != null)
            product.setSubCategory(productDao.getSubCategory());
        if (productDao.getItemName() != null)
            product.setItemName(productDao.getItemName());
        if (productDao.getItemType() != null)
            product.setItemType(productDao.getItemType());
        if(productDao.getPrice() != null)
            product.setPrice(productDao.getPrice());
        if (productDao.getDescription() != null)
            product.setDescription(productDao.getDescription());
        if(productDao.getImage() != null)
            product.setImage(productDao.getImage().getBytes());
        productsRepository.save(product);
        return "Product Updated Successfully";
    }

    public String updateProductFully(Long id, ProductDao productDao) throws IOException {
        Products product = getProductById(id);
        product.setMainCategory(productDao.getMainCategory());
        product.setSubCategory(productDao.getSubCategory());
        product.setItemType(productDao.getItemType());
        product.setItemName(productDao.getItemName());
        product.setPrice(productDao.getPrice());
        product.setDescription(productDao.getDescription());
        product.setImageName(productDao.getImage().getOriginalFilename());
        product.setImageType(productDao.getImage().getContentType());
        product.setImage(productDao.getImage().getBytes());
        productsRepository.save(product);
        return "Product Updated Successfully";
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
    public String deleteAllByItemName(String itemName){
        productsRepository.deleteAll(getProductsByItemName(itemName));
        return "All products successfully deleted for category "+itemName;
    }

    @Transactional
    public String deleteAllByItemType(String itemType){
        productsRepository.deleteAll(getProductsBySubCategory(itemType));
        return "All products successfully deleted for category "+itemType;
    }

    public boolean isProductExistById(Long id){
        return productsRepository.existsById(id);
    }

}
