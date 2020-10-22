package com.vnb.RestaurantVote.repository;

import com.vnb.RestaurantVote.model.Meals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meals, Integer> {

    List<Meals> getMealByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
