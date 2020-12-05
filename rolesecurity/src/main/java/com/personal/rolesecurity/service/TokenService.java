package com.personal.rolesecurity.service;

import java.time.Instant;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class TokenService {

	public Supplier<String> tokenSupplier = null;

	private TokenService() {
	}

	public String getNewToken() {

		if (tokenSupplier == null) {
			tokenSupplier = () -> {
				StringBuilder token = new StringBuilder();
				long currentTimeInMilisecond = Instant.now().toEpochMilli();
				return token.append(currentTimeInMilisecond).append("-").append(UUID.randomUUID().toString())
						.toString();
			};
		}

		return tokenSupplier.get();

	}

}
