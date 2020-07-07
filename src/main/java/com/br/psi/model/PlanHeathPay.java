package com.br.psi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlanHeathPay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double value;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlanHeath planheath;
	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public PlanHeath getPlanheath() {
		return planheath;
	}

	public void setPlanheath(PlanHeath planheath) {
		this.planheath = planheath;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
