package com.vnb.RestaurantVote.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnb.RestaurantVote.model.Meal;

import java.time.LocalDate;
import java.util.Objects;

public class MealTo {

    private Integer id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    private Integer votes;

    public MealTo() {
    }

    public MealTo(Meal meal, Integer votes) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.createdDate = meal.getCreatedDate();
        this.votes = votes;
    }

    public MealTo(Integer id, String name, LocalDate createdDate, Integer votes) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", votes=" + votes +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealTo)) return false;
        MealTo mealTo = (MealTo) o;
        return Objects.equals(id, mealTo.id) &&
                Objects.equals(name, mealTo.name) &&
                Objects.equals(createdDate, mealTo.createdDate) &&
                Objects.equals(votes, mealTo.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdDate,  votes);
    }
}
