package com.asd.controller;

import com.asd.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/requests_report")
	public ResponseEntity<String> requestsReport(@RequestParam("email") String email,
												 @RequestParam(value = "limit", required = false) Integer limit) {
		ResponseEntity<String> response;
		try {
			reportService.getRequestReport(email, limit);
			response = ResponseEntity.ok("Report created and sent to email: " + email);
		} catch (AuthorizationException e) {
			log.info("User not allowed to execute method 'getRequestReport'");
			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not allowed to execute method 'getRequestReport'");
		} catch (IOException e) {
			log.error("Report hasn't created", e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Report hasn't created");
		}
		return response;
	}
}
