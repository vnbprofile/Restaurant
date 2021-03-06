package com.vnb.restaurantvote.controller;

import com.vnb.restaurantvote.model.Meal;
import com.vnb.restaurantvote.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.vnb.restaurantvote.utils.DateTimeUtil.adjustEndDateTime;
import static com.vnb.restaurantvote.utils.DateTimeUtil.adjustStartDateTime;


@RestController
@RequestMapping("meal")
public class MealController {

    private static Logger log = LoggerFactory.getLogger(MealController.class);

    private final MealService mealService;

    @Autowired
    public MealController(MealService MealService) {
        this.mealService = MealService;
    }

    @GetMapping
    public List<Meal> getAll() {
        log.info("LOG список ,блюд получен");
        return mealService.getAll();
    }

    @GetMapping("{id}")
    public Meal get(@PathVariable("id") int id) {
        log.info("LOG блюд с id: {} найдено", id);
        return mealService.getById(id);
    }

    @GetMapping("/filter")
    public List<Meal> getBetween(@RequestParam(name = "startDate", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   LocalDate start,
                                 @RequestParam(name = "endDate", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   LocalDate end) {

        return mealService.getByDateOrBetweenDateTimes(adjustStartDateTime(start), adjustEndDateTime(end));
    }
}

