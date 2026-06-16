package com.ltr.service;

import com.ltr.dao.OrderDao;
import com.ltr.module.Orders;
import com.ltr.exception.OrderNotFoundException;
import com.ltr.exception.ProductNotFoundException;
import com.ltr.mapper.Mapper;
import com.ltr.module.Products;
import com.ltr.module.Users;
import com.ltr.repository.OrdersRepository;
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
        return Mapper.mapToOrderAndProductAndUser(allOrders);
    }

    public OrderDao getOrderById(Long id){
        Orders order = ordersRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found For id "+id));
        return Mapper.mapToOrderAndProduct(order);
    }

    @Transactional
    public List<OrderDao> getOrdersByUserId(Long id){
         List<Orders> order = ordersRepository.findAllByUser_Id(id);
         return Mapper.mapToOrderAndProduct(order);
    }

    public String updateOrderById(Long id, OrderDao updateOrder){
        Orders order = ordersRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found For id "+id));

        order.setTotalAmount(updateOrder.getTotalAmount());
        order.setPlacedDate(order.getPlacedDate());
        order.setStatus("Placed");
        order.setBodyMeasurement(updateOrder.getBodyMeasurement());
        Users user = new Users();
        user.setId(updateOrder.getUsersDao().getId());
        Products product = new Products();
        product.setId(updateOrder.getProductDao().getId());
        order.setUser(user);
        order.setProduct(product);
        ordersRepository.save(order);
        return "Order Updated Successfully...";
    }
}
