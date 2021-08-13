package com.example.essence.controller;

import com.example.essence.model.product.Category;
import com.example.essence.model.product.Color;
import com.example.essence.model.product.Product;
import com.example.essence.repo.product.CategoryRepository;
import com.example.essence.repo.product.ColorRepository;
import com.example.essence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ColorRepository colorRepository;

    @GetMapping("/add-product")
    public String getAddProductsPage(Model model){
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("allColors", colorRepository.findAll());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addNewProduct(@RequestParam Map<String, String> form,
                                @RequestParam MultipartFile[] list_img,
                                Model model){
        try {
            Product product = new Product();
            Set<Category> productCategories = categoryRepository.findAll().stream()
                    .filter(category -> form.containsKey(category.getName()))
                    .collect(Collectors.toSet());
            Set<Color> productColors = colorRepository.findAll().stream()
                    .filter(color -> form.containsKey(color.getName()))
                    .collect(Collectors.toSet());

            product.setName( form.get("name") );
            product.setPrice( Double.parseDouble(form.get("price")) );
            product.setOldPrice( Double.parseDouble(form.get("old_price")) );
            product.setCategories(productCategories);
            product.setColors(productColors);
            product.setSold(0);
            product.setLoading_date(new Date());

            productService.save(product, list_img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/products/add-product";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id,
                                HttpServletRequest request,
                                Model model){
        Product product = productService.getById(id);
        try {
            productService.delete(product);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
