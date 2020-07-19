package com.br.psi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
	public static final Long ACTIVE = Long.valueOf(1);
	public static final Long BLOCKED = Long.valueOf(2);
	public static final Long CANCELED = Long.valueOf(3);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	public Status(Long id) {
		this.id= id;
	}
	
	public Status() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
