package com.br.psi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateStart;
	private Date dateEnd;
	private Integer dayOfWeek;
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	private Professional professional;
	@ManyToOne(fetch = FetchType.EAGER)
	private Kind kind;
	@ManyToOne(fetch = FetchType.EAGER)
	private PaymentPatient paymentPatient;
	@ManyToOne(fetch = FetchType.EAGER)
	private OfficeRoom officeRoom;
	private Date createDate;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanCode planCode;

	public Schedule() {
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

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public PaymentPatient getPaymentPatient() {
		return paymentPatient;
	}

	public void setPaymentPatient(PaymentPatient paymentPatient) {
		this.paymentPatient = paymentPatient;
	}

	public OfficeRoom getOfficeRoom() {
		return officeRoom;
	}

	public void setOfficeRoom(OfficeRoom officeRoom) {
		this.officeRoom = officeRoom;
	}

	public PlanCode getPlanCode() {
		return planCode;
	}

	public void setPlanCode(PlanCode planCode) {
		this.planCode = planCode;
	}
	
	

}
