package com.example.BuildPC.service;

import com.example.BuildPC.dto.OrderDTO;
import com.example.BuildPC.model.Order;
import com.example.BuildPC.model.OrderDetail;
import com.example.BuildPC.repository.OrderDetailRepository;
import com.example.BuildPC.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;
    public List<OrderDTO> listAllOrder(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orderList) {
            float total = calculateTotal(order);
            orderDTOS.add(new OrderDTO(order,total));
        }
        return orderDTOS;
    }
    public void deleteOrderById(int id){
        orderRepository.deleteById(id);
    }
    public Order getOrderById(int id){
        return orderRepository.findById(id).get();
    }
    public float calculateTotal(Order order){
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
        float total = 0;

        for (OrderDetail orderDetail : orderDetails) {
            float productSalePrice = orderDetail.getProduct().getProductSalePrice();
            int quantity = orderDetail.getQuantity();
            float discount = orderDetail.getDiscount() != null ? orderDetail.getDiscount() : 1;

            total += quantity * productSalePrice * discount;
        }
        return total;
    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

}
