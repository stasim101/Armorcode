package com.personal.rolesecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.personal.rolesecurity.model.Role;
import com.personal.rolesecurity.model.User;
import com.personal.rolesecurity.repository.RoleRepository;
import com.personal.rolesecurity.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public String createNewUser(String name, String email, String designation, String organization)
			throws JsonProcessingException {
		Role role = roleRepository.save(new Role(designation, tokenService.getNewToken(), organization));
		User user = userRepository.save(new User(name, email, role));
		return user.toString();
	}

	public String getOrganizationName(String token) {
		Optional<Role> roleOp = roleRepository.getRoleByToken(token);
		if (roleOp.isPresent())
			return roleOp.get().getOrganization();
		return "Invalid";
	}

}
