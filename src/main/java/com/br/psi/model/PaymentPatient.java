package com.br.psi.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
public class PaymentPatient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "{campo.obrigatorio}")
	private String rg;
	@NotBlank(message = "{campo.obrigatorio}")
	private String cpf;
	@NotBlank(message = "{campo.obrigatorio}")
	private String name;
	private String numberPlan;
	private Integer amount;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	private Payment payment;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanHeath planHealth;
	private Double value;
	@ManyToOne(fetch = FetchType.EAGER)
	private Formation formation;
	@Transient
	private Integer amountConsumo;
	private Date createDate;
	@Column(length = 8000, nullable = true)
	private String note;
	
	public PaymentPatient() {
		super();
		this.createDate = new Date();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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

	public PlanHeath getPlanHealth() {
		return planHealth;
	}

	public void setPlanHealth(PlanHeath planHealth) {
		this.planHealth = planHealth;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberPlan() {
		return numberPlan;
	}

	public void setNumberPlan(String numberPlan) {
		this.numberPlan = numberPlan;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Integer getAmountConsumo() {
		return amountConsumo;
	}

	public void setAmountConsumo(Integer amountConsumo) {
		this.amountConsumo = amountConsumo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
