package com.boots.controller;

import com.boots.entity.Order;
import com.boots.entity.User;
import com.boots.repository.OrderRepository;
import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/orders")
    public String getOrders(){
        return "orders";
    }

    @RequestMapping("/createOrders")
    public String getCreateOrders(){
        return "createOrders";
    }

    @RequestMapping("/listOrders")
    public String getListOrders(){
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

        userService.saveOrder(order);

        return "redirect:/";
    }
}
