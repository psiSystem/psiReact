package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Specialty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Long cbo;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private GroupSpecialty groupSpecialty;

	public GroupSpecialty getGroupSpecialty() {
		return groupSpecialty;
	}

	public void setGroupSpecialty(GroupSpecialty groupSpecialty) {
		this.groupSpecialty = groupSpecialty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCbo() {
		return cbo;
	}

	public void setCbo(Long cbo) {
		this.cbo = cbo;
	}

}
