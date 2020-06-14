package com.br.psi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class DayWeek {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private OfficeRoom officeRoom;
	private String day;
	private Boolean enabled;
	@Transient
	private List<Shifts> listShifts;
	
	public List<Shifts> getListShifts() {
		return listShifts;
	}

	public void setListShifts(List<Shifts> listShifts) {
		this.listShifts = listShifts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OfficeRoom getOfficeRoom() {
		return officeRoom;
	}

	public void setOfficeRoom(OfficeRoom officeRoom) {
		this.officeRoom = officeRoom;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
}
