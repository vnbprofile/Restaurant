package com.vnb.RestaurantVote.repository;

import com.vnb.RestaurantVote.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> getRestaurantByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
