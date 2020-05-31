package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Professional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Person person;
	@NotNull
	private Long registerProfessional;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	private Specialty specialty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getRegisterProfessional() {
		return registerProfessional;
	}

	public void setRegisterProfessional(Long registerProfessional) {
		this.registerProfessional = registerProfessional;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

}
