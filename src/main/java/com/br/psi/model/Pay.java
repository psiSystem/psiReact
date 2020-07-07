package com.br.psi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	private Professional professional;
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	@ManyToOne(fetch = FetchType.LAZY)
	private Schedule schedule;
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	private PlanHeath planHeath;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private PaymentPatient paymentPatient;
	@ManyToOne(fetch = FetchType.EAGER)
	private StatusPay statusPay;
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public PlanHeath getPlanHeath() {
		return planHeath;
	}

	public void setPlanHeath(PlanHeath planHeath) {
		this.planHeath = planHeath;
	}

	public PaymentPatient getPaymentPatient() {
		return paymentPatient;
	}

	public void setPaymentPatient(PaymentPatient paymentPatient) {
		this.paymentPatient = paymentPatient;
	}

	public StatusPay getStatusPay() {
		return statusPay;
	}

	public void setStatusPay(StatusPay statusPay) {
		this.statusPay = statusPay;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
