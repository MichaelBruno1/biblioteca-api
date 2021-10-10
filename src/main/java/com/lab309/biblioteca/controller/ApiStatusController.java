package com.lab309.biblioteca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab309.biblioteca.model.ApiStatus;

@RestController
@RequestMapping("/api")
public class ApiStatusController {

	@GetMapping
	@RequestMapping("/status")
	public ResponseEntity<ApiStatus> status(){
		ApiStatus status = new ApiStatus("v1", "OK");
		
		return ResponseEntity.ok(status);
	}
}
