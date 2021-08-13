package com.example.essence.repo.product;

import com.example.essence.model.product.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {

}
