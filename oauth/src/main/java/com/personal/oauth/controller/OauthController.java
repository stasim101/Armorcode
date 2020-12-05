package com.personal.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

	@GetMapping("/user")
	public void user(@AuthenticationPrincipal OAuth2User principal,HttpServletResponse response) throws IOException {
		 String name =  principal.getAttribute("name");
		 String email = principal.getAttribute("email");
		 String url = "http://localhost:2222/all?name="+name+"&email="+email;
		 response.setHeader("Location", url);
		 response.setStatus(302);
	}

}
