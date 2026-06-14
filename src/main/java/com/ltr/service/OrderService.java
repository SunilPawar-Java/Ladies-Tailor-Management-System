package com.ltr.service;

import com.ltr.dao.order.OrderDao;
import com.ltr.entity.orders.Orders;
import com.ltr.exception.classes.OrderNotFoundException;
import com.ltr.exception.classes.ProductNotFoundException;
import com.ltr.mapper.Mapper;
import com.ltr.repository.order.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final ProductsService productsService;
    private final OrdersRepository ordersRepository;

    public OrderService(ProductsService productsService, OrdersRepository ordersRepository) {
        this.productsService = productsService;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public String saveOrder(Orders order){
        order.setPlacedDate(LocalDateTime.now());
        order.setStatus("Placed");
        if (!productsService.isProductExistById(order.getProduct().getId()))
            throw new ProductNotFoundException("Fail to place order because product is not available for id " + order.getProduct().getId());
        ordersRepository.save(order);
        return "Order Placed...";
    }

    @Transactional
    public List<OrderDao> getAllOrders(){
        List<Orders> allOrders = ordersRepository.findAll();
        return Mapper.parseToOrderAndProductAndUser(allOrders);
    }

    public OrderDao getOrderById(Long id){
        Orders order = ordersRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found For id "+id));
        return Mapper.parseToOrderAndProduct(order);
    }

    @Transactional
    public List<OrderDao> getOrdersByUserId(Long id){
         List<Orders> order = ordersRepository.findAllByUser_Id(id);
         return Mapper.parseToOrderAndProduct(order);
    }
}
