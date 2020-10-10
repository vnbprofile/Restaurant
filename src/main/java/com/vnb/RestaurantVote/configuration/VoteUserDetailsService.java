package com.vnb.RestaurantVote.configuration;

import com.vnb.RestaurantVote.AuthorizedUser;
import com.vnb.RestaurantVote.model.User;
import com.vnb.RestaurantVote.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VoteUserDetailsService implements UserDetailsService {

    final UserService userService;

    public VoteUserDetailsService(UserService userService) {
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
