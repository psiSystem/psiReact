package com.br.psi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Professional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Person person;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Client Client;
	private Long registerProfessional;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Specialty speciality;

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

	public Client getClient() {
		return Client;
	}

	public void setClient(Client client) {
		Client = client;
	}

	public Long getRegisterProfessional() {
		return registerProfessional;
	}

	public void setRegisterProfessional(Long registerProfessional) {
		this.registerProfessional = registerProfessional;
	}

	public Specialty getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Specialty speciality) {
		this.speciality = speciality;
	}

	

}
