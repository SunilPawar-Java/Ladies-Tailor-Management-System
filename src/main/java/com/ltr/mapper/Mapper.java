package com.ltr.mapper;

import com.ltr.dao.ProductDao;
import com.ltr.dao.UsersDao;
import com.ltr.dao.OrderDao;
import com.ltr.module.Orders;
import com.ltr.module.Users;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static OrderDao mapToOrderAndProduct(Orders order){
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

    private static @NonNull ProductDao mapToProductDao(Orders order) {
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

    public static List<OrderDao> mapToOrderAndProduct(List<Orders> orders){
        List<OrderDao> opdaoList = new ArrayList<>();
        orders.forEach(nextOrder ->{
            opdaoList.add(mapToOrderAndProduct(nextOrder));
        });
        return opdaoList;
    }

    public static UsersDao mapToUserDao(Users user){
        UsersDao usersDetails = new UsersDao();
        usersDetails.setId(user.getId());
        usersDetails.setFullName(user.getFullName());
        usersDetails.setPhone(user.getPhone());
        usersDetails.setEmail(user.getEmail());
        usersDetails.setRegistrationDate(user.getRegistrationDate());
        return usersDetails;
    }

    public static List<OrderDao> mapToOrderAndProductAndUser(List<Orders> orders){
        List<OrderDao> opdaoList = new ArrayList<>();
        UsersDao usersDao = new UsersDao();
        orders.forEach(nextOrder ->{
            usersDao.setId(nextOrder.getUser().getId());
            usersDao.setFullName(nextOrder.getUser().getFullName());
            usersDao.setEmail(nextOrder.getUser().getEmail());
            usersDao.setPhone(nextOrder.getUser().getPhone());
            OrderDao orderDao = mapToOrderAndProduct(nextOrder);
            orderDao.setUsersDao(usersDao);
            opdaoList.add(orderDao);
        });
        return opdaoList;
    }
}
