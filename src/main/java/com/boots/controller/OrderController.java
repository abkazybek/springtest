package com.boots.controller;

import com.boots.entity.Order;
import com.boots.entity.User;
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


    @Autowired
    private UserService userService;

    @GetMapping("/createOrders")
    public String registration(Model model) {
        model.addAttribute("orderForm", new Order());

        return "createOrders";
    }

    @PostMapping("/createOrders")
    public String addUser(@ModelAttribute("orderForm") @Valid Order orderForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "createOrders";
        }

        model.addAttribute("orderForm", new Order());

        return "redirect:/";
    }
}
