package com.example.essence.repo.product;

import com.example.essence.model.product.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, String> {
    List<Type> findByCategory_Name(String name);

    Type findByName(String name);
}
