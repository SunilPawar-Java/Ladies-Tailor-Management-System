package com.ltr.mapper;

import com.ltr.dao.ProductDao;
import com.ltr.dao.UsersDao;
import com.ltr.dao.OrderDao;
import com.ltr.model.Orders;
import com.ltr.model.Products;
import com.ltr.model.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static OrderDao mapToOrderAndProductDao(Orders order){
        OrderDao opDao = new OrderDao();
        opDao.setId(order.getId());
        opDao.setTotalAmount(order.getTotalAmount());
        opDao.setPlacedDate(order.getPlacedDate());
        opDao.setStatus(order.getStatus());
        opDao.setUsersDao(null);
        opDao.setBodyMeasurement(order.getBodyMeasurement());
        ProductDao productDao = mapToProductDao(order);
        opDao.setProductDao(productDao);
        return opDao;
    }

    private static ProductDao mapToProductDao(Orders order) {
        ProductDao productDao = new ProductDao();
        productDao.setId(order.getProduct().getId());
        productDao.setItemName(order.getProduct().getItemName());
        productDao.setItemType(order.getProduct().getItemType());
        productDao.setPrice(order.getProduct().getPrice());
        productDao.setMainCategory(order.getProduct().getMainCategory());
        productDao.setSubCategory(order.getProduct().getSubCategory());
        productDao.setDescription(order.getProduct().getDescription());
        productDao.setImage(null);
        return productDao;
    }

    public static List<OrderDao> mapToOrderAndProductDao(List<Orders> orders){
        List<OrderDao> opdaoList = new ArrayList<>();
        orders.forEach(nextOrder -> opdaoList.add(mapToOrderAndProductDao(nextOrder)));
        return opdaoList;
    }

    public static List<OrderDao> mapToOrderAndProductAndUser(List<Orders> orders){
        List<OrderDao> opdaoList = new ArrayList<>();
        UsersDao usersDao = new UsersDao();
        orders.forEach(nextOrder ->{
            usersDao.setId(nextOrder.getUser().getId());
            usersDao.setFullName(nextOrder.getUser().getFullName());
            usersDao.setEmail(nextOrder.getUser().getEmail());
            usersDao.setPhone(nextOrder.getUser().getPhone());
            usersDao.setAddress(nextOrder.getUser().getAddress());
            OrderDao orderDao = mapToOrderAndProductDao(nextOrder);
            orderDao.setUsersDao(usersDao);
            opdaoList.add(orderDao);
        });
        return opdaoList;
    }

    public static Products mapToProducts(ProductDao productDao) throws IOException {
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
        return product;
    }

    public static UsersDao mapToUserDao(Users user){
        UsersDao usersDao = new UsersDao();
        usersDao.setId(user.getId());
        usersDao.setFullName(user.getFullName());
        usersDao.setPhone(user.getPhone());
        usersDao.setEmail(user.getEmail());
        usersDao.setAddress(user.getAddress());
        usersDao.setRegistrationDate(user.getRegistrationDate());
        return usersDao;
    }

    public static List<UsersDao> mapToUserDao(List<Users> users){
        List<UsersDao> usersDao = new ArrayList<>();
        users.forEach(user -> usersDao.add(mapToUserDao(user)));
        return usersDao;
    }

    public static ProductDao mapToProductDao(Products product){
        ProductDao productDao = new ProductDao();
        productDao.setItemName(product.getItemName());
        productDao.setItemType(product.getItemType());
        productDao.setMainCategory(product.getMainCategory());
        productDao.setSubCategory(product.getSubCategory());
        productDao.setPrice(product.getPrice());
        productDao.setDescription(product.getDescription());
        productDao.setImage(null);
        return productDao;
    }

    public static List<ProductDao> mapToProductDao(List<Products> products){
        List<ProductDao> productDaoList = new ArrayList<>();
        products.forEach(products1 -> {
            productDaoList.add(mapToProductDao(products1));
        });
        return productDaoList;
    }
}
