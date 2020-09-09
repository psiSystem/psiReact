package com.br.psi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PlanHeathClient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanHeath planHeath;
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;
	@ManyToOne(fetch = FetchType.EAGER)
	private Formation formation;
	private Double value;
	private Date createDate;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanCode planCode;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "professional_plan_heath_client", joinColumns = @JoinColumn(name = "plan_heath_client_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private List<Professional> professionalPlanHeathClient;

	public PlanHeathClient() {
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

	public PlanHeath getPlanHeath() {
		return planHeath;
	}

	public void setPlanHeath(PlanHeath planHeath) {
		this.planHeath = planHeath;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public PlanCode getPlanCode() {
		return planCode;
	}

	public void setPlanCode(PlanCode planCode) {
		this.planCode = planCode;
	}

	public List<Professional> getProfessionalPlanHeathClient() {
		return professionalPlanHeathClient;
	}

	public void setProfessionalPlanHeathClient(List<Professional> professionalPlanHeathClient) {
		this.professionalPlanHeathClient = professionalPlanHeathClient;
	}

}
