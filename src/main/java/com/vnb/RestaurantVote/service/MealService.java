package com.vnb.RestaurantVote.service;

import com.vnb.RestaurantVote.model.Meals;
import com.vnb.RestaurantVote.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meals> getAll() {
        return mealRepository.findAll();
    }

    public Meals getById(int id) {
        return find(id);
    }


    public Meals save(Meals meal) {
        return mealRepository.save(meal);
    }

    public void update(Meals meal) {
        mealRepository.save(meal);
    }

    public void delete(int id) {
        Meals meal = find(id);
        mealRepository.delete(meal);
    }


    public List<Meals> getByDateOrBetweenDateTimes(@Nullable LocalDate startDateTime, @Nullable LocalDate endDateTime) {
        return mealRepository.getMealByCreatedDateBetween(startDateTime, endDateTime);
    }

    private Meals find(int id) {
        return mealRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
