package com.webapi.knudon.controller;

import com.webapi.knudon.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class GreetingController {

	@Autowired
	private GreetingService greetingService;

	@PostMapping("/execute")
	public String executeInterpreter(@RequestBody String input) {
		try {
			return greetingService.executeInterpreter(input);
		} catch (IOException e) {
			return "Error: " + e.getMessage();
		}
	}
}