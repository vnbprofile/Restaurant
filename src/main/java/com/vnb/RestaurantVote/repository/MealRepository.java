package com.vnb.RestaurantVote.repository;

import com.vnb.RestaurantVote.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> getMealByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
