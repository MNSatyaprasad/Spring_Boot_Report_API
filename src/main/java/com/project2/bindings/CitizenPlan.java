package com.project2.bindings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="citizen_plan")
public class CitizenPlan {
	
	@Id
	@GeneratedValue
	private Integer cid;
	private  String planName;
	private String planStatus;
	private String cname;
	private String genrder;
	private Long phno;
	private Long ssn;
	

}
