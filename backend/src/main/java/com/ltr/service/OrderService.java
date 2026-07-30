package com.ltr.service;

import com.ltr.dao.OrderDao;
import com.ltr.exception.UserNotFoundException;
import com.ltr.model.*;
import com.ltr.exception.OrderNotFoundException;
import com.ltr.mapper.Mapper;
import com.ltr.product.Products;
import com.ltr.product.ProductsService;
import com.ltr.repository.OrdersRepository;
import com.ltr.user.Users;
import com.ltr.user.UsersRepository;
import com.ltr.security.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final ProductsService productsService;
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final AuthService authService;
    private final DesignService designService;

    public OrderService(ProductsService productsService, OrdersRepository ordersRepository, UsersRepository usersRepository, AuthService authService, DesignService designService) {
        this.productsService = productsService;
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
        this.authService = authService;
        this.designService = designService;
    }

    @Transactional
    public Long saveOrder(OrderRequest orderRequest){
        Orders order = Mapper.mapToOrder(orderRequest);
        Users user = usersRepository.findById(authService.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found!"));
        order.setUser(user);
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setPlacedDate(LocalDateTime.now());
        order.setStatus(PaymentStatus.PAYMENT_PENDING.toString());
        order.setProduct(productsService.getProductById(orderRequest.getProductId()));
        Orders savedOrder = ordersRepository.save(order);
        return savedOrder.getId();
    }

    public OrderDao getOrderById(Long id){
        Orders order = ordersRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found For id "+id));
        return Mapper.mapToOrderAndProductDao(order);
    }

    @Transactional
    public List<OrderDao> getAllOrders(){
        List<Orders> allOrders = ordersRepository.findAll();
        return Mapper.mapToOrderAndProductAndUser(allOrders);
    }

    @Transactional
    public List<OrderDao> getOrdersByUserId(Long id){
         List<Orders> order = ordersRepository.findAllByUser_Id(id);
         return Mapper.mapToOrderAndProductDao(order);
    }

    public String updateOrderById(Long id, OrderDao updateOrder){
        Orders order = ordersRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order Not Found For id "+id));

        order.setTotalAmount(updateOrder.getTotalAmount());
        order.setPlacedDate(order.getPlacedDate());
        order.setStatus("Placed");
        order.setBodyMeasurement(updateOrder.getBodyMeasurement());
        Users user = usersRepository.findById(authService.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found!"));
        Products product = new Products();
        product.setId(updateOrder.getProductDao().getId());
        order.setUser(user);
        order.setProduct(product);
        ordersRepository.save(order);
        return "Order Updated Successfully...";
    }

    public String cancelOrder(Long id, String status){
        if (!ordersRepository.existsById(id)){
            throw new OrderNotFoundException("Order Not Found for id "+id);
        }
        ordersRepository.updateStatus(id,status);
        return "Order Canceled Successfully...";
    }
}
