package com.project2.runner;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.project2.bindings.CitizenPlan;
import com.project2.repository.CitizenPlanRepository;

@Component
public class ReportApiRunner implements ApplicationRunner {
   
	@Autowired
	private CitizenPlanRepository repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		CitizenPlan c1 = new CitizenPlan();
		c1.setCname("Jhon");
		c1.setGenrder("Male");
		c1.setPhno(667788877l);
		c1.setSsn(677888789l);
		c1.setPlanName("SNAP");
		c1.setPlanStatus("Approved");
		
		CitizenPlan c2 = new CitizenPlan();
		c2.setCname("Smith");
		c2.setGenrder("Male");
		c2.setPhno(9999788877l);
		c2.setSsn(77777888789l);
		c2.setPlanName("SNAP");
		c2.setPlanStatus("Denied");
		
		CitizenPlan c3 = new CitizenPlan();
		c3.setCname("Chareles");
		c3.setGenrder("FeMale");
		c3.setPhno(1111188877l);
		c3.setSsn(22228789l);
		c3.setPlanName("CCAP");
		c3.setPlanStatus("Approved");
		
		CitizenPlan c4 = new CitizenPlan();
		c4.setCname("Peteri");
		c4.setGenrder("FeMale");
		c4.setPhno(33338877l);
		c4.setSsn(777788789l);
		c4.setPlanName("CCAP");
		c4.setPlanStatus("Denied");
		
		List<CitizenPlan> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
	
		repo.saveAll(list);
		
	}
	

}
