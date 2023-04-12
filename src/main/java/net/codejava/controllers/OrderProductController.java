package net.codejava.controllers;
import net.codejava.entity.Order;
import net.codejava.entity.OrderProduct;
import net.codejava.service.OrderProductService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderProductController {

    @Autowired
    private OrderProductService cartServices;

    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model) {
        List<OrderProduct> orderProduct = cartServices.listCartItems();
        model.addAttribute("orderProduct", orderProduct);
        return "shopping_cart";

    }
}
