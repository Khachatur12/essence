package com.example.essence.controller;

import com.example.essence.model.product.Product;
import com.example.essence.model.user.User;
import com.example.essence.repo.user.UserRepository;
import com.example.essence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String getHomePage(){
        return "redirect:/";
    }

    @GetMapping("/")
    public String getHomePage(@AuthenticationPrincipal User user,
                              Model model){
        model.addAttribute("user", user);
        List<Product> popular_products = productService.getAllSortedBySoldAndLimit(12);
        model.addAttribute("popular_products_n1", popular_products.subList(0, 6));
        model.addAttribute("popular_products_n2", popular_products.subList(6, 12));
        return "/home";
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getTestPage(@AuthenticationPrincipal User user,
                              Model model){
        model.addAttribute("user", user);
        return "/test";
    }
}