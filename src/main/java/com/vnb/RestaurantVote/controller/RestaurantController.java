package com.vnb.RestaurantVote.controller;

import com.vnb.RestaurantVote.model.Restaurant;
import com.vnb.RestaurantVote.model.RestoranMeal;
import com.vnb.RestaurantVote.model.User;
import com.vnb.RestaurantVote.service.RestaurantService;
import com.vnb.RestaurantVote.to.RestaurantTo;
import com.vnb.RestaurantVote.utils.RestaurantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.vnb.RestaurantVote.utils.DateTimeUtil.adjustEndDateTime;
import static com.vnb.RestaurantVote.utils.DateTimeUtil.adjustStartDateTime;
import static com.vnb.RestaurantVote.utils.RestaurantUtil.createWithVote;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private static Logger log = LoggerFactory.getLogger(RestaurantController.class);

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("LOG список ресторанов получен");
        return RestaurantUtil.getRestaurantWithVotes(restaurantService.getAll());
    }

    @GetMapping("{id}")
    public RestaurantTo get(@PathVariable("id") int id) {
        log.info("LOG ресторанов с id: {} найдено", id);
        return createWithVote(restaurantService.getById(id));
    }

    @GetMapping("meals/{id}")
    public List<RestoranMeal> getRestoranMeal(@PathVariable("id") int id) {
        log.info("LOG меню ресторанов с id: {} найдено", id);
        return restaurantService.getRestoranMeal(id);
    }

    @GetMapping("vote/{id}")
    public List<RestaurantTo> vote(@AuthenticationPrincipal User currentUser, @PathVariable("id") int restaurantId) {
        restaurantService.vote(currentUser, restaurantId);
        log.info("LOG голос отправлен");
        return RestaurantUtil.getRestaurantWithVotes(restaurantService.getAll());
    }

    @GetMapping("/filter")
    public List<RestaurantTo> getBetween(@RequestParam(name = "startDate", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   LocalDate start,
                                         @RequestParam(name = "endDate", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   LocalDate end) {
        List<Restaurant> restaurantDateFiltered = restaurantService.getByDateOrBetweenDateTimes(adjustStartDateTime(start), adjustEndDateTime(end));
        return RestaurantUtil.getRestaurantWithVotes(restaurantDateFiltered);
    }
}

