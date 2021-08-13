package com.example.essence.model.product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category{
    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_type",
            joinColumns = @JoinColumn(name = "category_name"),
            inverseJoinColumns = @JoinColumn(name = "type_name"))
    private Set<Type> types = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Type> types) {
        this.name = name;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", types=" + types +
                '}';
    }
}
