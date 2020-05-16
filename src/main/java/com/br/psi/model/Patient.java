package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Person person;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Client client;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private CameClinic cameClinic;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private PlanHeath planHeath;
	@Column(nullable = false)
	private String emergencialPhone;
	@Column(nullable = false)
	private String numberPlanHealth;

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

}
