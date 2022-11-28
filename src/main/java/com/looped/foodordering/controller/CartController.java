package com.looped.foodordering.controller;

import com.looped.foodordering.model.Cart;
import com.looped.foodordering.model.Order;
import com.looped.foodordering.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: looped
 * DateTime: 2022/11/28 15:35
 */
@Controller
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/cart")
    public String getCarts(Model model) {

        List<Cart> listCart = cartService.getAllCarts();

        Double total = listCart.stream()
                .map(cart -> cart.getPrice().multiply(BigDecimal.valueOf(cart.getNum())))
                .mapToDouble(b -> b.doubleValue())
                .sum();

        model.addAttribute("listCart", listCart);
        model.addAttribute("total", total);
        model.addAttribute("order", new Order());
        return "cart";
    }

    @GetMapping("/saveCart")
    public String saveOrder(@RequestParam("menuId") Long menuId) {
        cartService.saveCartItem(menuId);
        return "redirect:/";
    }

}
