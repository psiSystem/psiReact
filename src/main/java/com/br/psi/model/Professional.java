package com.br.psi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Professional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Person person;
	@NotNull(message = "{campo.obrigatorio}")
	private Long registerProfessional;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	private Formation formation;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="formation_specialty", joinColumns = @JoinColumn(name="professional_id"), inverseJoinColumns = @JoinColumn(name="specialty_id"))
    private List<Specialty> specialtys;
	@ManyToOne(fetch = FetchType.EAGER)
	private Status status;
	private Date createDate;

	public Professional() {
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

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Specialty> getSpecialtys() {
		return specialtys;
	}

	public void setSpecialtys(List<Specialty> specialtys) {
		this.specialtys = specialtys;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
