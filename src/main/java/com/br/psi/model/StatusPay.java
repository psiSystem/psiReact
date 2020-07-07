package com.br.psi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusPay {
	
	public static final Long PAID = Long.valueOf(1);
	public static final Long PENDING = Long.valueOf(2);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String decription;
	
	private StatusPay(Long id, String decription) {
		this.id = id;
		this.decription = decription;
	}

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
	
}
