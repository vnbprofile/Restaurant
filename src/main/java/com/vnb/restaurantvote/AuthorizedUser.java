package com.vnb.restaurantvote;

import com.vnb.restaurantvote.model.Role;
import com.vnb.restaurantvote.model.User;

import java.util.Set;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private int id;
    private Set<Role> roles;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.id = user.getId();
        this.roles = user.getRoles();
    }

    public int getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
