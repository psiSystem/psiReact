package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
public class PaymentPatient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rg;
	private String cpf;
	@ManyToOne(fetch = FetchType.EAGER)
	private Payment payment;
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanHeath planHeath;
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PlanHeath getPlanHeath() {
		return planHeath;
	}

	public void setPlanHeath(PlanHeath planHeath) {
		this.planHeath = planHeath;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	
}
