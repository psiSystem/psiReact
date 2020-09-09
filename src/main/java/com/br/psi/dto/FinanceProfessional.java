package com.br.psi.dto;

import java.util.Date;

import com.br.psi.model.Client;
import com.br.psi.model.Professional;

public class FinanceProfessional {

	private Integer amount;
	private Double grossAmount;
	private String professionalName;
	private String patientName;
	private String planHeath;
	private String payment;
	private Double valuePlan;
	private Date dateStart;
	private Date dateEnd;
	

	private Client client;
	private Professional professional;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getProfessionalName() {
		return professionalName;
	}

	public void setProfessionalName(String professionalName) {
		this.professionalName = professionalName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPlanHeath() {
		return planHeath;
	}

	public void setPlanHeath(String planHeath) {
		this.planHeath = planHeath;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Double getValuePlan() {
		return valuePlan;
	}

	public void setValuePlan(Double valuePlan) {
		this.valuePlan = valuePlan;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}


}
