package com.vnb.restaurantvote.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Restaurant {

    public static final int RESTAURANT_START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "RESTAURANT_START_SEQ", sequenceName = "RESTAURANT_START_SEQ", allocationSize = 1, initialValue = RESTAURANT_START_SEQ)
    @GeneratedValue(generator = "RESTAURANT_START_SEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 60)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "restaurant_votes",
            joinColumns = {@JoinColumn(name = "restaurant_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false)})
    @BatchSize(size = 20)
    private Set<User> votes = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "meals", joinColumns = @JoinColumn(name = "restaurant_id"))
    private List<RestaurantMeal> restaurantMeals = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, LocalDate createdDate, List<RestaurantMeal> restaurantMeals) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.restaurantMeals = restaurantMeals;
    }

    public Restaurant(Integer id, String name, LocalDate createdDate, List<RestaurantMeal> restaurantMeals, Set<User> votes) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.restaurantMeals = restaurantMeals;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Set<User> getVotes() {
        return votes;
    }

    public void setVotes(Set<User> votes) {
        this.votes = votes;
    }

    public List<RestaurantMeal> getRestaurantMeals() {
        return restaurantMeals;
    }

    public void setRestoranMeals(List<RestaurantMeal> restaurantMeals) {
        this.restaurantMeals = restaurantMeals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", restaurantMeals=" + restaurantMeals +
                '}';
    }
}
