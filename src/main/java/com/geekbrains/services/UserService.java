package com.geekbrains.services;

import com.geekbrains.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String userName);

//    void save(SystemUser systemUser);
}
