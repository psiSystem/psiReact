package com.br.psi.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.br.psi.dto.FilterCalendar;
import com.br.psi.model.Client;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Professional;
import com.br.psi.model.Shifts;

public interface ShiftsRepositoryService  {

	List<Object> findByTimeAvailable(FilterCalendar filter, Client client);

	List<Shifts> findByProfessionalAndProfessionalFormation(FilterCalendar filter, Client client);

	
}