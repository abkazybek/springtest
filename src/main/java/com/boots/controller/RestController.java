package com.boots.controller;

import com.boots.entity.Order;
import com.boots.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    OrderService orderService;

    @Autowired
    public RestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/order")
    public ResponseEntity<?> create(@RequestBody Order order) {
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/order")
    public ResponseEntity<List<Order>> read() {
        final List<Order> orders = orderService.allOrders();

        return orders != null &&  !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Order> read(@PathVariable(name = "id") int id) {
        final Order order = orderService.read(id);

        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/order/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Order order) {
        final boolean updated = orderService.update(order, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = orderService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
