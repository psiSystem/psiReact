package com.br.psi.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(nullable = true)
	private Integer dayOfWeek;
	private Boolean enabled;
	@Transient
	private List<Shifts> listShifts;
	private Date createDate;

	public DayWeek() {
		super();
		this.createDate = new Date();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public static int dayWeek(DayWeek day) {
		int dayWeek = 0;
		switch (day.getDay()) {
		case "Segunda Feira":
			dayWeek = Calendar.MONDAY - 1;
			break;
		case "Terça Feira":
			dayWeek = Calendar.TUESDAY - 1;
			break;
		case "Quarta Feira":
			dayWeek = Calendar.WEDNESDAY - 1;
			break;
		case "Quinta Feira":
			dayWeek = Calendar.THURSDAY - 1;
			break;
		case "Sexta Feira":
			dayWeek = Calendar.FRIDAY - 1;
			break;
		case "Sábado":
			dayWeek = Calendar.SATURDAY - 1;
			break;
		case "Domingo":
			dayWeek = Calendar.SUNDAY - 1;
			break;
		default:
			break;
		}
		return dayWeek;
	}
}
