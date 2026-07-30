package com.ltr.mapper;

import com.ltr.dao.DesignDao;
import com.ltr.product.ProductDao;
import com.ltr.product.Products;
import com.ltr.user.UsersDao;
import com.ltr.dao.OrderDao;
import com.ltr.model.*;
import com.ltr.user.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static OrderDao mapToOrderAndProductDao(Orders order) {
        ProductDao productDao = mapToProductDao(order);
        return OrderDao.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .placedDate(order.getPlacedDate())
                .status(order.getStatus())
                .usersDao(null)
                .bodyMeasurement(order.getBodyMeasurement())
                .productDao(productDao)
                .build();
    }

    private static ProductDao mapToProductDao(Orders order) {
        return ProductDao.builder()
                .id(order.getProduct().getId())
                .itemName(order.getProduct().getItemName())
                .itemType(order.getProduct().getItemType())
                .price(order.getProduct().getPrice())
                .mainCategory(order.getProduct().getMainCategory())
                .locationCategory(order.getProduct().getLocationCategory())
                .description(order.getProduct().getDescription())
                .image(null)
                .build();
    }

    public static List<OrderDao> mapToOrderAndProductDao(List<Orders> orders) {
        List<OrderDao> opdaoList = new ArrayList<>();
        orders.forEach(nextOrder -> opdaoList.add(mapToOrderAndProductDao(nextOrder)));
        return opdaoList;
    }

    public static List<OrderDao> mapToOrderAndProductAndUser(List<Orders> orders) {
        List<OrderDao> opdaoList = new ArrayList<>();
        UsersDao usersDao = new UsersDao();
        orders.forEach(nextOrder -> {
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
        return Products.builder()
                .mainCategory(productDao.getMainCategory())
                .locationCategory(productDao.getLocationCategory())
                .itemType(productDao.getItemType())
                .itemName(productDao.getItemName())
                .price(productDao.getPrice())
                .description(productDao.getDescription())
                .color(productDao.getColor())
                .occasion(productDao.getOccasion())
                .season(productDao.getSeason())
                .stock(productDao.getStock())
                .discountPercentage(productDao.getDiscountPercentage())
                .fabric(productDao.getFabric())
                .lengthType(productDao.getLengthType())
                .sleeveType(productDao.getSleeveType())
                .sleeveLength(productDao.getSleeveLength())
                .neckline(productDao.getNeckline())
                .fitType(productDao.getFitType())
                .hemline(productDao.getHemline())
                .closureType(productDao.getClosureType())
                .imageName(productDao.getImage().getOriginalFilename())
                .imageType(productDao.getImage().getContentType())
                .image(productDao.getImage().getBytes())
                .build();
    }

    public static UsersDao mapToUserDao(Users user) {
        return UsersDao.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress())
                .registrationDate(user.getRegistrationDate())
                .build();
    }

    public static List<UsersDao> mapToUserDao(List<Users> users) {
        List<UsersDao> usersDao = new ArrayList<>();
        users.forEach(user -> usersDao.add(mapToUserDao(user)));
        return usersDao;
    }

    public static ProductDao mapToProductDao(Products product) {
        return ProductDao.builder()
                .itemName(product.getItemName())
                .itemType(product.getItemType())
                .mainCategory(product.getMainCategory())
                .locationCategory(product.getLocationCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(null)
                .build();
    }

    public static List<ProductDao> mapToProductDao(List<Products> products) {
        List<ProductDao> productDaoList = new ArrayList<>();
        products.forEach(products1 -> {
            productDaoList.add(mapToProductDao(products1));
        });
        return productDaoList;
    }

    public static Design mapToDesign(DesignDao designDao) {
        return Design.builder()
                .clothMaterial(designDao.getClothMaterial())
                .clothColor(designDao.getClothColor())
                .frontNeck(designDao.getFrontNeck())
                .backNeck(designDao.getBackNeck())
                .sleeve(designDao.getSleeve())
                .fitting(designDao.getFitting())
                .openingClosing(designDao.getOpeningClosing())
                .build();
    }

    public static DesignDao mapToDesignDao(Design design) {
        return DesignDao.builder()
                .clothMaterial(design.getClothMaterial())
                .clothColor(design.getClothColor())
                .frontNeck(design.getFrontNeck())
                .backNeck(design.getBackNeck())
                .sleeve(design.getSleeve())
                .fitting(design.getFitting())
                .openingClosing(design.getOpeningClosing())
                .build();
    }

    public static Orders mapToOrder(OrderRequest orderRequest){
        Orders order = new Orders();
        Design design = Design.builder()
                .clothMaterial(orderRequest.getClothMaterial())
                .clothColor(orderRequest.getClothColor())
                .frontNeck(orderRequest.getFrontNeck())
                .backNeck(orderRequest.getBackNeck())
                .fitting(orderRequest.getFitting())
                .sleeve(orderRequest.getSleeve())
                .openingClosing(orderRequest.getOpeningClosing())
                .build();

        BodyMeasurement bodyMeasurement = BodyMeasurement.builder()
                .size(orderRequest.getSize())
                .upperFullLength(orderRequest.getUpperFullLength())
                .bust(orderRequest.getBust())
                .waist(orderRequest.getWaist())
                .shoulder(orderRequest.getShoulder())
                .armholeRound(orderRequest.getArmholeRound())
                .sleeveLength(orderRequest.getSleeveLength())
                .sleeveRound(orderRequest.getSleeveRound())
                .frontNeckDepth(orderRequest.getFrontNeckDepth())
                .backNeckDepth(orderRequest.getBackNeckDepth())
                .bottomFullLength(orderRequest.getBottomFullLength())
                .hip(orderRequest.getHip())
                .rise(orderRequest.getRise())
                .thigh(orderRequest.getThigh())
                .knee(orderRequest.getKnee())
                .calf(orderRequest.getCalf())
                .ankle(orderRequest.getAnkle())
                .instep(orderRequest.getInstep())
                .message(orderRequest.getMessage())
                .build();
        order.setDesign(design);
        order.setBodyMeasurement(bodyMeasurement);
        return order;
    }
}
