package com.project2.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.bindings.CitizenPlan;
import com.project2.bindings.SearchRequest;
import com.project2.service.CitizenService;

@RestController
public class CitizenPlanController {

	@Autowired
	private CitizenService service;
	
	@GetMapping("/planname")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> citizenplans =service.getPlanName();
		return new ResponseEntity<>(citizenplans,HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanstatus(){
		List<String> citizenstatus =service.getPlanStatus();
		return new ResponseEntity<>(citizenstatus,HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> getPlanStatusandPlanName(@RequestBody SearchRequest request){
		  List<CitizenPlan> search =service.getCitixenPlan(request);
		  return new ResponseEntity<>(search,HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		 
		String headerKey ="Content-Disposition";
		String headerValue = "attachmentfilename=citizenplans.xlsx";
		
		response.setHeader(headerKey, headerValue);
		service.exportExcel(response);
	}
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception{
		
		response.setContentType("applicatuon/octet-stream");
		
		String headerkey = "Content Disposition";
		String headerValue = "attachment;filename=citizenplans.pdf";
		
		response.setHeader(headerkey, headerValue);
		service.exportPdf(response);
	}
	
}
