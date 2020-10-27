package com.vnb.RestaurantVote.model;


import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class RestaurantMeal {

    private String name;

    private Integer price;

    public RestaurantMeal() {
    }

    public RestaurantMeal(String name, Integer price) {
        this.name = name;
        this.price = price;
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


    @Override
    public String toString() {
        return "RestaurantMeals{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantMeal)) return false;
        RestaurantMeal restaurantMeal = (RestaurantMeal) o;
        return name.equals(restaurantMeal.name) &&
                price.equals(restaurantMeal.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
