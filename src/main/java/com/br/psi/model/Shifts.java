package com.br.psi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shifts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private DayWeek dayWeek;
	private Date timeStart;
	private Date timeEnd;
	@ManyToOne(fetch = FetchType.EAGER)
	private Professional professional;
	private Date createDate;
	private Boolean timeAvailable;

	public Shifts() {
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

	public DayWeek getDayWeek() {
		return dayWeek;
	}

	public void setDayWeek(DayWeek dayWeek) {
		this.dayWeek = dayWeek;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Boolean getTimeAvailable() {
		return timeAvailable;
	}

	public void setTimeAvailable(Boolean timeAvailable) {
		this.timeAvailable = timeAvailable;
	}

}
