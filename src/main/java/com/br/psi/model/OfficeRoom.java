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
import javax.validation.constraints.NotBlank;

@Entity
public class OfficeRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;
	@NotBlank(message = "{campo.obrigatorio}")
	private String decription;
	@Transient 
	private List<DayWeek> listDayWeek;
	
	
	public List<DayWeek> getListDayWeek() {
		return listDayWeek;
	}

	public void setListDayWeek(List<DayWeek> listDayWeek) {
		this.listDayWeek = listDayWeek;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

}
