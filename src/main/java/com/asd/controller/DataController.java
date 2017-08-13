package com.asd.controller;

import com.asd.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DataController {

	@Autowired
	private DataService dataService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/responses")
	public ResponseEntity requestsList(@RequestParam(value = "limit", required = false) Integer limit) {
		try {
			return ResponseEntity.ok(dataService.getRequests(limit));
		} catch (AuthorizationException e) {
			log.info("User not allowed to execute method 'getRequests'");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not allowed to execute method 'getRequests'");
		}
	}
}
