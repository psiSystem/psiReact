package com.br.psi.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.br.psi.validation.UniqueCpf;
import com.br.psi.validation.UniqueEmail;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "{campo.obrigatorio}")
	@Column(nullable = false)
	private String name;
	@Valid
	@ManyToOne(fetch = FetchType.EAGER)
	private Gender gender;
	@NotNull(message = "{campo.obrigatorio}")
	@Column(nullable = false)
	private Date bornDate;
	/*
	 * @NotBlank(message = "{campo.obrigatorio}")
	 * 
	 * @Email
	 */
	@Column(nullable = false, unique = true)
	private String email;
	@NotBlank(message = "{campo.obrigatorio}")
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false)
	@NotBlank(message = "{campo.obrigatorio}")
	private String rg;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@Valid
	private Anddress anddress;
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;
	@NotBlank(message = "{campo.obrigatorio}")
	private String phone;
	private Date createDate;

	public Person() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBornDate() throws ParseException {

		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Anddress getAnddress() {
		return anddress;
	}

	public void setAnddress(Anddress anddress) {
		this.anddress = anddress;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
