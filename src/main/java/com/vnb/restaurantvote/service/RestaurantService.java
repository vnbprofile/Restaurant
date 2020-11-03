package com.vnb.restaurantvote.service;

import com.vnb.restaurantvote.model.Restaurant;
import com.vnb.restaurantvote.model.RestaurantMeal;
import com.vnb.restaurantvote.model.User;
import com.vnb.restaurantvote.repository.RestaurantRepository;
import com.vnb.restaurantvote.utils.exception.VoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.vnb.restaurantvote.utils.TimeUtil.canVote;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(int id) {
        return find(id);
    }

    public List<RestaurantMeal> getRestoranMeals(int id) {
        return find(id).getRestaurantMeals();
    }


    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        Restaurant restaurant = find(id);
        restaurantRepository.delete(restaurant);
    }

    public void vote(User user, int restaurantId) {
        Restaurant restaurant = find(restaurantId);
        Set<User> votes = restaurant.getVotes();
        if (!canVote(restaurant)) {
            throw new VoteException();
        }
        if (votes.contains(user)) {
            votes.remove(user);
        } else {
            votes.add(user);
        }
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getByDateOrBetweenDateTimes(@Nullable LocalDate startDateTime, @Nullable LocalDate endDateTime) {
        return restaurantRepository.getRestaurantByCreatedDateBetween(startDateTime, endDateTime);
    }

    private Restaurant find(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
