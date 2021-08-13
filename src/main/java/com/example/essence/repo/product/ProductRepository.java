package com.example.essence.repo.product;

import com.example.essence.model.product.Category;
import com.example.essence.model.product.Color;
import com.example.essence.model.product.Product;
import com.example.essence.model.product.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    List<Product> findByNameLike(String text);

    List<Product> findByCategories(Category category);

    List<Product> findByTypes(Type type);

    List<Product> findByColors(Color color);

    List<Product> findByCategoriesAndTypes(Category category, Type type);

}
