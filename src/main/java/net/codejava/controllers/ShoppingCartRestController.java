package net.codejava.controllers;

import net.codejava.service.OrderProductService;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestController {
    @Autowired
    private OrderProductService cartService;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Integer productId,
                                   @PathVariable("qty") Integer quantity,
                                   @AuthenticationPrincipal Authentication authentication) {
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "You must login to add this product to your shopping cart.";
//        }

        Integer addedQuantity = cartService.addProduct(Long.valueOf(productId), quantity);
        return addedQuantity + " item(s) of this product were added to your shopping cart:";

    }
}
