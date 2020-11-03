package com.vnb.restaurantvote.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnb.restaurantvote.model.Meal;

import java.time.LocalDate;
import java.util.Objects;

public class MealTo {

    private Integer id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    public MealTo() {
    }

    public MealTo(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.createdDate = meal.getCreatedDate();
    }

    public MealTo(Integer id, String name, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
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

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                "}";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealTo)) return false;
        MealTo mealTo = (MealTo) o;
        return Objects.equals(id, mealTo.id) &&
                Objects.equals(name, mealTo.name) &&
                Objects.equals(createdDate, mealTo.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdDate);
    }
}
