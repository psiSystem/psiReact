package com.br.psi.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.br.psi.dto.FilterCalendar;
import com.br.psi.model.Client;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Professional;
import com.br.psi.model.Shifts;

public interface ShiftsRepositoryService  {

	Boolean findByTimeAvailable(Integer dayOfWeek, Professional professional, LocalDateTime localStart,LocalDateTime localEned, OfficeRoom officeRoom);

	List<Shifts> findByProfessionalAndProfessionalFormation(FilterCalendar filter, Client client);

	
}