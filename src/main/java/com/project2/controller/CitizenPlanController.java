package com.project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.project2.service.CitizenService;

@RestController
public class CitizenPlanController {

	@Autowired
	private CitizenService service;
	
	
}
