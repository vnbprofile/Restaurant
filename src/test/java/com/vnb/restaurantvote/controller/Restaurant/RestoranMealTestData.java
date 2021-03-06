package com.vnb.restaurantvote.controller.Restaurant;


import com.vnb.restaurantvote.model.Restaurant;
import com.vnb.restaurantvote.model.RestaurantMeal;
import com.vnb.restaurantvote.to.RestaurantTo;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;

import static com.vnb.restaurantvote.utils.TestUtil.readListFromJsonMvcResult;
import static com.vnb.restaurantvote.controller.user.UserTestData.VOTES;
import static com.vnb.restaurantvote.model.Restaurant.RESTAURANT_START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestoranMealTestData {

    public static final int RESTAURANT_ID = RESTAURANT_START_SEQ + 3;
    public static final int RESTAURANT_FOR_VOTE = RESTAURANT_START_SEQ + 7;
    public static final int RESTAURANT_FILTERED = RESTAURANT_START_SEQ + 5;

    public static final RestaurantMeal RestaurantMeal1 = new RestaurantMeal("Пирожок", 15);
    public static final RestaurantMeal RestaurantMeal2 = new RestaurantMeal("Чебурек", 30);

    public static final List<RestaurantMeal> RestaurantMeals = List.of(RestaurantMeal1, RestaurantMeal2);

    public static final Restaurant RESTAURANT_TO_TEST_1 = new Restaurant(1, "Restaurant 464", LocalDate.of(2019, 12, 12), List.of(new RestaurantMeal("Tost", 55)));
    public static final Restaurant RESTAURANT_TO_TEST_2 = new Restaurant(2, "Zakusochnaay", LocalDate.of(2019, 12, 12), List.of(new RestaurantMeal("Ceg", 90)));
    public static final List<Restaurant> RESTAURANTS_TO_TESTS = List.of(RESTAURANT_TO_TEST_1, RESTAURANT_TO_TEST_2);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Ресторан", LocalDate.of(2020, 10, 10), RestaurantMeals);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Ресторан1", LocalDate.of(2020, 10, 11), RestaurantMeals, VOTES);
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(RestaurantMeal actual, RestaurantMeal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(RestaurantTo actual, RestaurantTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meals").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(RestaurantTo... expected) {
        return contentJson(List.of(expected));
    }

    public static ResultMatcher contentJson(Iterable<RestaurantTo> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, RestaurantTo.class)).isEqualTo(expected);
    }

    public static ResultMatcher contentJson(List<RestaurantMeal> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, RestaurantMeal.class)).isEqualTo(expected);
    }
}
