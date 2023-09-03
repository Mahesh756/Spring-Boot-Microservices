package com.example.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.limitsservice.bean.Limits;
import com.example.microservices.limitsservice.configuration.Configurationn;

@RestController
public class LimitsController {
	
	@Autowired
	private Configurationn configuration;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
//		return new Limits(1,1000);
	}
}
