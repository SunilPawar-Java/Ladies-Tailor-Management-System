package com.ltr.mapper;

import com.ltr.dao.UsersDao;
import com.ltr.dao.order.OrderDao;
import com.ltr.entity.orders.Orders;
import com.ltr.entity.products.Products;
import com.ltr.entity.users.Users;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static OrderDao parseToOrderAndProduct(Orders order){
        OrderDao opDao = new OrderDao();
        opDao.setId(order.getId());
        opDao.setTotalAmount(order.getTotalAmount());
        opDao.setPlacedDate(order.getPlacedDate());
        opDao.setStatus(order.getStatus());
        opDao.setUsersDao(null);
        opDao.setBodyMeasurement(order.getBodyMeasurement());
        Products pro = order.getProduct();
        pro.setImage(new byte[0]);
        opDao.setProduct(pro);

        return opDao;
    }

    public static List<OrderDao> parseToOrderAndProduct(List<Orders> orders){
        List<OrderDao> opdaoList = new ArrayList<OrderDao>();
        orders.forEach(nextOrder ->{
            opdaoList.add(parseToOrderAndProduct(nextOrder));
        });
        return opdaoList;
    }

    public static UsersDao parseToUserDao(Users user){
        UsersDao usersDetails = new UsersDao();
        usersDetails.setId(user.getId());
        usersDetails.setFullName(user.getFullName());
        usersDetails.setPhone(user.getPhone());
        usersDetails.setEmail(user.getEmail());
        usersDetails.setRegistrationDate(user.getRegistrationDate());
        return usersDetails;
    }

    public static List<OrderDao> parseToOrderAndProductAndUser(List<Orders> orders){
        List<OrderDao> opdaoList = new ArrayList<OrderDao>();
        UsersDao usersDao = new UsersDao();
        orders.forEach(nextOrder ->{
            usersDao.setId(nextOrder.getUser().getId());
            usersDao.setFullName(nextOrder.getUser().getFullName());
            usersDao.setEmail(nextOrder.getUser().getEmail());
            usersDao.setPhone(nextOrder.getUser().getPhone());
            OrderDao orderDao = parseToOrderAndProduct(nextOrder);
            orderDao.setUsersDao(usersDao);
            opdaoList.add(orderDao);
        });
        return opdaoList;
    }
}
