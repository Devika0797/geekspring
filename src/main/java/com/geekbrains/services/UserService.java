package com.geekbrains.services;

import com.geekbrains.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    User findByUsername(String userName);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
