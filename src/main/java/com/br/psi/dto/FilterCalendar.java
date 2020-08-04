package com.br.psi.dto;

import com.br.psi.model.Formation;
import com.br.psi.model.Professional;

public class FilterCalendar {
	private Formation formation;
	private Professional professional;

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

}
