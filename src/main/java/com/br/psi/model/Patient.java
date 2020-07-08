package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Person person;
	@ManyToOne(fetch = FetchType.EAGER)
	private CameClinic cameClinic;
	@NotBlank(message = "{campo.obrigatorio}")
	@Column(nullable = false)
	private String emergencialPhone;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public CameClinic getCameClinic() {
		return cameClinic;
	}

	public void setCameClinic(CameClinic cameClinic) {
		this.cameClinic = cameClinic;
	}

	public String getEmergencialPhone() {
		return emergencialPhone;
	}

	public void setEmergencialPhone(String emergencialPhone) {
		this.emergencialPhone = emergencialPhone;
	}

}
