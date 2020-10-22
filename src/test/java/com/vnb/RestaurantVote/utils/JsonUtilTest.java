package com.vnb.RestaurantVote.utils;

import com.vnb.RestaurantVote.model.Restaurant;
import com.vnb.RestaurantVote.utils.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vnb.RestaurantVote.RestoranMealTestData.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JsonUtilTest {

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(RESTAURANTS_TO_TESTS);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        assertMatch(restaurants, RESTAURANTS_TO_TESTS);
    }

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(RESTAURANT_TO_TEST_1);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        assertMatch(restaurant, RESTAURANT_TO_TEST_1);
    }
}