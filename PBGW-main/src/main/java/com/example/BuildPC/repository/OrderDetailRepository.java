package com.example.BuildPC.repository;

import com.example.BuildPC.model.Order;
import com.example.BuildPC.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
List<OrderDetail> findByOrder(Order order);

}
