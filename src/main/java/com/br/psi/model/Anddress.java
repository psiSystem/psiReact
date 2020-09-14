package com.br.psi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Anddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotBlank(message = "{campo.obrigatorio}")
	private String anddress;
	@Column(nullable = false)
	/* @NotBlank(message = "{campo.obrigatorio}") */
	private String neighborhood;
	@Column(nullable = false)
	@NotBlank(message = "{campo.obrigatorio}")
	private String city;
	@Column(nullable = false)
	@NotBlank(message = "{campo.obrigatorio}")
	private String state;
	@Column(nullable = false)
	@NotBlank(message = "{campo.obrigatorio}")
	private String country;
	@Column(nullable = true)
	private String complement;
	@Column(nullable = false)
	@NotBlank(message = "{campo.obrigatorio}")
	private String cep;
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnddress() {
		return anddress;
	}

	public void setAnddress(String anddress) {
		this.anddress = anddress;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

}
