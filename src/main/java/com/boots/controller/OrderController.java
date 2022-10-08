package com.boots.controller;

import com.boots.entity.Order;
import com.boots.entity.OrderJSON;
import com.boots.repository.OrderRepository;
import com.boots.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }

    //страница orders
    @RequestMapping("/orders")
    public String getOrders(){
        return "orders";
    }

    //страница создания заявки
    @RequestMapping("/createOrders")
    public String getCreateOrders(){
        return "createOrders";
    }

    //список товаров
    @RequestMapping("/listOrders")
    public String getListOrders(Model model){

        model.addAttribute("allOrders", orderService.allOrders());

        return "listOrders";
    }


    @GetMapping("/createOrders")
    public String registrationOrder(Model model) {
        model.addAttribute("orderForm", new Order());
        return "createOrders";
    }

    @PostMapping("/createOrders")
    public String addOrder(@ModelAttribute("orderForm") Order orderForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createOrders";
        }
        Order order = new Order();
        order.setId(orderForm.getId());
        order.setName(orderForm.getName());
        order.setAddres(orderForm.getAddres());
        order.setAmount(orderForm.getAmount());
        order.setTelephone(orderForm.getTelephone());
        order.setThing(orderForm.getThing());
        model.addAttribute("orderForm", order);
        orderService.saveOrder(order);
        return "redirect:/";
    }

    @RequestMapping(value = "/createOrders/1", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addOrder(OrderJSON orderJSON) {

        Order order = new Order();
        order.setId(orderJSON.getId());
        order.setName(orderJSON.getName());
        order.setAddres(orderJSON.getAddres());
        order.setAmount(orderJSON.getAmount());
        order.setTelephone(orderJSON.getTelephone());
        order.setThing(orderJSON.getThing());
        orderService.saveOrder(order);
        return "Создался новый товар!";
    }
}
