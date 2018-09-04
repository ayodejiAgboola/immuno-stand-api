package com.immune.immunostand.repository;

import com.immune.immunostand.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Ayodeji.Agboola on 3/27/2017.
 */
public interface UserDao extends CrudRepository<User, String> {
    User findByUsername(String username);
    Optional<User> findById(String id);
    User findByEmail(String email);
}
