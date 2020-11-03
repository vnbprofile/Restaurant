package com.vnb.restaurantvote.configuration;

import com.vnb.restaurantvote.AuthorizedUser;
import com.vnb.restaurantvote.model.User;
import com.vnb.restaurantvote.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetail implements UserDetailsService {

    final UserService userService;

    public UserDetail(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =  userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new AuthorizedUser(user);
    }
}
