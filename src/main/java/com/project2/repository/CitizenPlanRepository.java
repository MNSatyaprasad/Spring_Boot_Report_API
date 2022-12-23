package com.project2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project2.bindings.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {

	@Query("select distct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
	
	@Query("select distct(planName) from CitizenPlan ")
	public List<String> getPlanName();
}
