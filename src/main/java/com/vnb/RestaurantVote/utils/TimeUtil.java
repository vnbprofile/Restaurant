package com.vnb.RestaurantVote.utils;

import com.vnb.RestaurantVote.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtil {

    public static int limitHourForVote = 11;

    private TimeUtil() {
        }

    public static boolean canVote(Restaurant restaurant) {
        if (restaurant.getCreatedDate().isEqual(LocalDate.now())) {
            return LocalDateTime.now().getHour() < limitHourForVote;
        }
        return false;
    }
}
