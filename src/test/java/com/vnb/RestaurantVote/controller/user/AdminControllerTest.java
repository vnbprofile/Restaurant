package com.vnb.RestaurantVote.controller.user;

import com.vnb.RestaurantVote.model.Restaurant;
import com.vnb.RestaurantVote.model.Role;
import com.vnb.RestaurantVote.model.User;
import com.vnb.RestaurantVote.service.RestaurantService;
import com.vnb.RestaurantVote.service.UserService;
import com.vnb.RestaurantVote.utils.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.vnb.RestaurantVote.controller.Restaurant.RestoranMealTestData.assertMatch;
import static com.vnb.RestaurantVote.controller.Restaurant.RestoranMealTestData.*;
import static com.vnb.RestaurantVote.utils.TestUtil.readFromJson;
import static com.vnb.RestaurantVote.controller.user.UserTestData.assertMatch;
import static com.vnb.RestaurantVote.controller.user.UserTestData.contentJson;
import static com.vnb.RestaurantVote.controller.user.UserTestData.*;
import static com.vnb.RestaurantVote.utils.json.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    private static final String REST_USER_URL = "/admin/users/";
    private static final String REST_RESTAURANT_URL = "/admin/restaurants/";



    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createRestaurant() throws Exception {
        Restaurant created = getCreated();

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(REST_RESTAURANT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(created)));

        Restaurant returned = readFromJson(actions, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateCafe() throws Exception {
        Restaurant updated = getUpdated();

        mockMvc.perform(MockMvcRequestBuilders.put(REST_RESTAURANT_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(restaurantService.getById(RESTAURANT_ID), updated);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteCafe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_RESTAURANT_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, null, LocalDate.now(), null);
        mockMvc.perform(MockMvcRequestBuilders.post(REST_RESTAURANT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(RESTAURANT_ID, null, null, null);
        mockMvc.perform(MockMvcRequestBuilders.put( REST_RESTAURANT_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(print());
    }



    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_USER_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(userService.getAll()));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getOneUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_USER_URL + ADMIN_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(userService.findById(ADMIN_ID)));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createUser() throws Exception {
        User expected = new User(null, "new@gmail.com", "newPass",   Role.ROLE_USER);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(expected, "newPass")))
                .andExpect(status().isCreated());
        User returned = readFromJson(action, User.class);

        expected.setId(returned.getId());
        assertMatch(returned, expected);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_USER_URL + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), List.of(ADMIN, USER2));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteNotFoundUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_USER_URL + 1))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateUser() throws Exception {
        User updated = new User(USER);
        updated.setEmail("UpdatedEmail");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));

        mockMvc.perform(MockMvcRequestBuilders.put(REST_USER_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithPassword(updated, USER.getPassword())))
                .andExpect(status().isNoContent());

        assertMatch(userService.findById(USER_ID), updated);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_USER_URL + "by?email=" + ADMIN.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN));
    }
}