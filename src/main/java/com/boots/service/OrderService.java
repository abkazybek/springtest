package com.boots.service;

import com.boots.entity.Order;
import com.boots.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    //сохранение в базу
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    //список из базы
    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    public void create(Order order) {
        orderRepository.save(order);
    }

    public Order read(int id) {
        return orderRepository.findById(id);
    }


    public boolean update(Order order, Long id) {
        List<Order> listOrders = orderRepository.findAll();
        if (listOrders.contains(id)) {
            order.setId(id);
            listOrders.add(order);
            return true;
        }
        return false;
    }

    public boolean delete(Long id) {
        List<Order> listOrders = orderRepository.findAll();
        return listOrders.remove(id.intValue()) != null;
    }
}
