package com.example.essence.model.product;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_type",
            joinColumns = @JoinColumn(name = "type_name"),
            inverseJoinColumns = @JoinColumn(name = "category_name"))
    private Category category;

    public Type() {}

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
