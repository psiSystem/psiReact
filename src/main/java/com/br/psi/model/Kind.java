package com.br.psi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kind {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String decription;
	private Integer amountDay;
	private Integer amountMultiple;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public Integer getAmountDay() {
		return amountDay;
	}
	public void setAmountDay(Integer amountDay) {
		this.amountDay = amountDay;
	}
	public Integer getAmountMultiple() {
		return amountMultiple;
	}
	public void setAmountMultiple(Integer amountMultiple) {
		this.amountMultiple = amountMultiple;
	}
	
	
	
}
