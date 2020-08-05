package com.br.psi.repository;

import java.time.LocalDateTime;

import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Professional;

public interface ShiftsRepositoryService  {

	Boolean findByTimeAvailable(Integer dayOfWeek, Professional professional, LocalDateTime localStart,LocalDateTime localEned, OfficeRoom officeRoom);

	
}