package com.personal.rolesecurity.bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.personal.rolesecurity.model.User;

public class MyUserDetails implements UserDetails {

	private String name;
	private String email;
	private String token;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.name = user.getName();
		this.token = user.getRole().getToken();
		this.email = user.getEmail();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getDesignation().toUpperCase()));
		/*
		 * Arrays.stream(user.getRole().getDesignation().split(",")).map(
		 * SimpleGrantedAuthority::new) .collect(Collectors.toList());
		 */
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return token;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}