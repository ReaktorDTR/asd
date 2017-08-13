package com.asd.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("user") String user, @RequestParam("password") String password) {
		Subject subject = SecurityUtils.getSubject();
		ResponseEntity<String> response;
		if (subject.isAuthenticated()) {
			response = ResponseEntity.ok("You already authenticated as " + user + '.');
		} else {
			AuthenticationToken token = new UsernamePasswordToken(user, password);
			try {
				subject.login(token);
				response = ResponseEntity.ok("Authenticated as " + user + '.');
			} catch (AuthenticationException e) {
				response = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("This combination of user:password not present in application");
			}
		}
		return response;
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return ResponseEntity.ok("You've logged out.");
	}

}
