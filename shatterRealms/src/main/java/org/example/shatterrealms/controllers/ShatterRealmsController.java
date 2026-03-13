package org.example.shatterrealms.controllers;

import org.example.shatterrealms.models.Member;
import org.example.shatterrealms.services.ShatterRealmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShatterRealmsController {
    @Autowired
    private ShatterRealmsService service;

    // Get request for static home/landing page
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    // Get requests for viewing cart
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", service.getCartItems());
        return "cart";
    }

    @GetMapping("/cart/add")
    public String addToCartForm(Model model) {
        model.addAttribute("products", service.getProducts());
        return "cart-add";
    }

    // Post request for adding new item to cart
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam int quantity) {
        service.addCartItem(productId, quantity);
        String name = service.findProductById(productId) != null
                ? service.findProductById(productId).getProductName() : "item";
        try { name = java.net.URLEncoder.encode(name, "UTF-8"); } catch (Exception e) { name = "item"; }
        return "redirect:/add/success/cart-item?name=" + name;
    }

    // Get request for viewing cart items
    @GetMapping("/cart/edit/{productId}")
    public String editCartItemForm(@PathVariable Long productId, Model model) {
        service.getCartItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .ifPresent(i -> model.addAttribute("editItem", i));
        return "cart-edit";
    }

    // Post request to update cart information
    @PostMapping("/cart/edit/{productId}")
    public String editCartItem(@PathVariable Long productId,
                               @RequestParam int quantity) {
        service.updateCartItem(productId, quantity);
        String name = service.findProductById(productId) != null
                ? service.findProductById(productId).getProductName() : "item";
        try { name = java.net.URLEncoder.encode(name, "UTF-8"); } catch (Exception e) { name = "item"; }
        return "redirect:/add/success/cart-update?name=" + name;
    }

    // Post request to delete cart item
    @PostMapping("/cart/delete/{productId}")
    public String deleteCartItem(@PathVariable Long productId) {
        service.removeCartItem(productId);
        return "redirect:/cart";
    }

    // Get request for success page
    @GetMapping("/add/success/{entity}")
    public String success(@PathVariable String entity,
                          @RequestParam(required = false, defaultValue = "record") String name,
                          Model model) {
        model.addAttribute("entity", entity);
        model.addAttribute("name", name);
        return "success";
    }
}
