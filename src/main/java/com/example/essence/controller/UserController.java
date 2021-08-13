package com.example.essence.controller;

import com.example.essence.model.user.User;
import com.example.essence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ProductService productService;

    @GetMapping("/account")
    public String getUserAccount(@AuthenticationPrincipal User user,
                                 Model model){

        model.addAttribute("user", user);
        return "account";
    }

    @GetMapping("/favorite_products")
    public String getFavoriteProducts(@AuthenticationPrincipal User user,
                                      Model model){

        model.addAttribute("user", user);
        return "account";
    }

    
}
