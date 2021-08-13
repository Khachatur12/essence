package com.example.essence.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Product name cant be empty")
    private String name;

    @Column(name = "price")
    @NotBlank(message = "Product price cant be empty")
    private Double price;

    @Column(name = "old_price")
    private Double oldPrice;

    @Column(name = "loading_date")
    @NotBlank(message = "Product loading date cant be empty")
    private Date loading_date;

    @Column(name = "sold")
    private int sold;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_name"))
    private Set<Color> colors = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_name"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_type",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_name"))
    private Set<Type> types = new HashSet<>();

    public Product() {}

    public Product(@NotBlank(message = "Product name cant be empty") String name, @NotBlank(message = "Product price cant be empty") double price, @NotBlank(message = "Product loading date cant be empty") Date loading_date, int sold) {
        this.name = name;
        this.price = price;
        this.loading_date = loading_date;
        this.sold = sold;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Date getLoading_date() {
        return loading_date;
    }

    public void setLoading_date(Date loading_date) {
        this.loading_date = loading_date;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", loading_date=" + loading_date +
                ", sold=" + sold +
                ", colors=" + colors +
                ", categories=" + categories +
                ", types=" + types +
                '}';
    }
}
