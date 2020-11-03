package com.vnb.restaurantvote.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "meals")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Meal {

    @Id
    private Integer id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @NotBlank
    @Column(name = "restaurant_id", nullable = false)
    private Integer restaurant_id;

    public Meal() {

    }

    public Meal(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Meal(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Meal(Integer id, String name, Integer price, Integer restaurant_id) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdDate=" + createdDate +
                ", restaurant_id=" + restaurant_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return name.equals(meal.name) &&
                price.equals(meal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
