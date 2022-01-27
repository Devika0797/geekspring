package com.geekbrains.repositories;

import com.geekbrains.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByUserName(String username);
}
