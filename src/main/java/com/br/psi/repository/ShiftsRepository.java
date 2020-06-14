package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;
import com.br.psi.model.DayWeek;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Shifts;

public interface ShiftsRepository extends JpaRepository<Shifts, String> {

	List<Shifts> findByDayWeekOfficeRoomClient(Client client);

	List<Shifts> findByDayWeekOfficeRoom(OfficeRoom officeRoom);
}