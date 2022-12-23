package com.project2.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.project2.bindings.CitizenPlan;
import com.project2.bindings.SearchRequest;

public interface CitizenService {

	
	public List<String> getPlanName();
	
	public List<String> getPlanStatus();
	
   public List<CitizenPlan> getCitixenPlan(SearchRequest request);
   
   public void exportExcel(HttpServletResponse reponse) throws Exception;
   
   public void exportPdf(HttpServletResponse reponse)throws Exception;
	
}
