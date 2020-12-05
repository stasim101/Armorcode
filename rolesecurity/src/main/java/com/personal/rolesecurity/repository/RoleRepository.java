package com.personal.rolesecurity.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.personal.rolesecurity.model.Role;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> getRoleByToken(String token);
}
