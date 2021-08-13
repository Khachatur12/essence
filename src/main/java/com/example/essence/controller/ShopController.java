package com.example.essence.controller;

import com.example.essence.model.product.Category;
import com.example.essence.model.product.Color;
import com.example.essence.model.product.Product;
import com.example.essence.model.product.Type;
import com.example.essence.model.user.User;
import com.example.essence.repo.product.CategoryRepository;
import com.example.essence.repo.product.ColorRepository;
import com.example.essence.repo.product.TypeRepository;
import com.example.essence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    private final TypeRepository typeRepository;

    private final ColorRepository colorRepository;

    public ShopController(ColorRepository colorRepository, ProductService productService, CategoryRepository categoryRepository, TypeRepository typeRepository) {
        this.colorRepository = colorRepository;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public String getShopPage(Model model) {
        try {
            model.addAttribute("products", productService.getAll());
            model.addAttribute("allCategories", categoryRepository.findAll());
            model.addAttribute("allColors", colorRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "errors-page";
        }
        return "shop";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam(value = "search_query") String search_query,
                                Model model) {
        try {
            model.addAttribute("products", productService.search(search_query));
            model.addAttribute("allCategories", categoryRepository.findAll());
            model.addAttribute("allColors", colorRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "errors-page";
        }
        return "shop";
    }

    @PostMapping("/search/filter")
    public String searchProductByFilter(@RequestParam Map<String, String> form,
                                        HttpServletRequest request,
                                        Model model) {
        try {
            String[] products_id = request.getParameterValues("product_id");
            List<Product> products;
            if (products_id == null)
                products = Collections.emptyList();
            else {
                products = new ArrayList<>(products_id.length);
                for (String s : products_id) {
                    products.add(productService.getById(Long.parseLong(s)));
                }
            }

            Category category = new Category(form.get("category_name"));
            Type type = new Type(form.get("type_name"));
            Color color = form.get("color_name") == null ? null : new Color(form.get("color_name"));
            Double min_price = form.get("min_price").equals("") ? null : Double.valueOf(form.get("min_price"));
            Double max_price = form.get("max_price").equals("") ? null : Double.valueOf(form.get("max_price"));

            model.addAttribute("products", productService.searchByFilter(products, min_price, max_price, color));
            model.addAttribute("allCategories", categoryRepository.findAll());
            model.addAttribute("allColors", colorRepository.findAll());
            model.addAttribute("category_name", category);
            model.addAttribute("type_name", type);
            model.addAttribute("min_price", min_price);
            model.addAttribute("max_price", max_price);

        } catch (Exception e) {
            model.addAttribute("error", e);
            return "errors-page";
        }
        return "shop";
    }

    @GetMapping("/search/filter/{category_name}")
    public String searchProductsByCategory(@PathVariable("category_name") String categoryName,
                                           Model model) {
        try {
            model.addAttribute("products", productService.searchByCategory(new Category(categoryName)));
            model.addAttribute("allCategories", categoryRepository.findAll());
            model.addAttribute("allColors", colorRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "errors-page";
        }
        return "shop";
    }

    @GetMapping("/search/filter/{category_name}/{type_name}")
    public String searchProductsByCategoryAndType(@PathVariable String category_name,
                                                  @PathVariable String type_name,
                                                  @RequestParam Map<String, Object> form,
                                                  Model model) {
        try {
            model.addAttribute("products", productService.searchByCategoryAndType(new Category(category_name), new Type(type_name)));
            model.addAttribute("allCategories", categoryRepository.findAll());
            model.addAttribute("allColors", colorRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "errors-page";
        }
        return "shop";
    }

    @PostMapping("/add_favorite_product")
    public String AddFavoriteProduct(@AuthenticationPrincipal User user,
                                     @RequestParam("product_id") Integer product_id,
                                     Model model) {
        try {
            if (user != null) {

            }
        } catch (Exception e) {

        }
        return "shop";
    }

}
