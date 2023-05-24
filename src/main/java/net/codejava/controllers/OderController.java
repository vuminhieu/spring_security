package net.codejava.controllers;

import net.codejava.entity.Order;
import net.codejava.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class OderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders/new")
    public String showNewForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("pageTitle", "Add New Order");

        return "/orders/form";
    }

    @PostMapping("/orders/save")
    public String saveOrder(Order order, RedirectAttributes ra) {
        service.save(order);
        ra.addFlashAttribute("message", "The order has been saved successfully.");

        return "orders/successful";
    }

}
