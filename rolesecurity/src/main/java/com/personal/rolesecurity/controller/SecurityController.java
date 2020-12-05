package com.personal.rolesecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.personal.rolesecurity.service.UserService;

@RestController
public class SecurityController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public String all(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
		return ("<h1>Welcome " + email + " : " + name + "</h1>");
	}

	@GetMapping("/create")
	public String createUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email,
			@RequestParam(value = "desn") String designation, @RequestParam(value = "org") String organization)
			throws JsonProcessingException {
		return userService.createNewUser(name, email, designation, organization);
	}

	@GetMapping("/organization")
	public String validateToken(HttpServletResponse response, @RequestParam("token") String token) throws IOException {
		/*
		 * response.sendRedirect( "http://localhost:3333/orgstatus?org=" +
		 * userService.getOrganizationName(token) + " &token = " + token);
		 */
		return userService.getOrganizationName(token);
	}

	@GetMapping("/admin")
	public String admin() {
		return ("<h1>Welcome admin</h1>");
	}

	@GetMapping("/developer")
	public String developer() {
		return ("<h1>Welcome developer</h1>");
	}

	@GetMapping("/secure")
	public String secure() {
		return ("<h1>Welcome Security Engineer</h1>");
	}

}
