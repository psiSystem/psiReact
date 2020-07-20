package com.br.psi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.psi.model.Client;
import com.br.psi.model.DayWeek;
import com.br.psi.model.Formation;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Shifts;

public interface ShiftsRepository extends JpaRepository<Shifts, String> {

	List<Shifts> findByDayWeekOfficeRoomClient(Client client);

	List<Shifts> findByDayWeekOfficeRoom(OfficeRoom officeRoom);
	@Query("select s from Shifts s where s.professional.formation = :formation")
	List<Shifts> findByProfessionalFormation(Formation formation);
	@Query("select s from Shifts s where s.professional.id is null")
	List<Shifts> findByProfessionalIsNull();
	
	List<Shifts> findByProfessionalId(Long id);
	@Query("select s from Shifts s where s.dayWeek.officeRoom.client = :client")
	List<Shifts> findAllByDayWeekOfficeRoomClient(Client client);
	
	
}