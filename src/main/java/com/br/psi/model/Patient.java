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
import javax.validation.constraints.NotNull;

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
	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanHeath planHeath;
	@NotBlank(message = "{campo.obrigatorio}")
	@Column(nullable = false)
	private String emergencialPhone;
	@NotBlank(message = "{campo.obrigatorio}")
	@Column(nullable = false)
	private String numberPlanHealth;
	
	@Transient
	private String password;

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

	public PlanHeath getPlanHeath() {
		return planHeath;
	}

	public void setPlanHeath(PlanHeath planHeath) {
		this.planHeath = planHeath;
	}

	public String getEmergencialPhone() {
		return emergencialPhone;
	}

	public void setEmergencialPhone(String emergencialPhone) {
		this.emergencialPhone = emergencialPhone;
	}

	public String getNumberPlanHealth() {
		return numberPlanHealth;
	}

	public void setNumberPlanHealth(String numberPlanHealth) {
		this.numberPlanHealth = numberPlanHealth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
