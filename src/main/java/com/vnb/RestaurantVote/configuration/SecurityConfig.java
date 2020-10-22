package com.vnb.RestaurantVote.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //для данных из бд
    private final UserDetail userDetail;

    @Autowired
    public SecurityConfig(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                    .authorizeRequests()
                    .antMatchers("/**/admin/**").hasRole("ADMIN")
                    .antMatchers("/users/profile/register").anonymous()
                    .anyRequest().authenticated()
                .and().csrf().disable();

        http.formLogin().permitAll().defaultSuccessUrl("/restaurants")
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/logout");

    }

    //для связи юзеров из бд, пароли оставил незашифрованными
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetail)
                            .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}
