package com.vnb.restaurantvote.service;

import com.vnb.restaurantvote.model.Meal;
import com.vnb.restaurantvote.repository.MealRepository;
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

    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

    public Meal getById(int id) {
        return find(id);
    }


    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    public void update(Meal meal) {
        mealRepository.save(meal);
    }

    public void delete(int id) {
        Meal meal = find(id);
        mealRepository.delete(meal);
    }


    public List<Meal> getByDateOrBetweenDateTimes(@Nullable LocalDate startDateTime, @Nullable LocalDate endDateTime) {
        return mealRepository.getMealByCreatedDateBetween(startDateTime, endDateTime);
    }

    private Meal find(int id) {
        return mealRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
