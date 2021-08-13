package com.example.essence.service;

import com.example.essence.model.product.Category;
import com.example.essence.model.product.Color;
import com.example.essence.model.product.Product;
import com.example.essence.model.product.Type;
import com.example.essence.model.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product getById(long id);

    List<Product> getAll();

    List<Product> getAllLimit(int limit);

    List<Product> getAllSortedBySoldAndLimit(int limit);

    List<Product> search(String searchQuery);

    List<Product> searchByFilter(List<Product> products, Double minPrice, Double maxPrice, Color color);

    List<Product> searchByFilter(Category category, Type type, Double minPrice, Double maxPrice, Color color);

    List<Product> searchByCategory(Category category);

    List<Product> searchByCategoryLike(String categoryName);

    List<Product> searchByType(Type type);

    List<Product> searchByTypeLike(String typeName);

    List<Product> searchByCategoryAndType(Category category, Type type);

    List<Product> searchByColor(Color color);

    List<Product> searchByColorLike(String colorName);

    void save(String name, Double price, MultipartFile[] list_img);

    void save(Product product, MultipartFile[] list_img) throws IOException;

    void delete(Product product) throws IOException;

}
