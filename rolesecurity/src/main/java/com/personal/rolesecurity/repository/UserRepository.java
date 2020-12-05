package com.personal.rolesecurity.repository;

import java.util.Optional;
import com.personal.rolesecurity.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

