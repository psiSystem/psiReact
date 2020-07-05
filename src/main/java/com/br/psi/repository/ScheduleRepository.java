package com.br.psi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.psi.model.Patient;
import com.br.psi.model.Professional;
import com.br.psi.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
	@Query("select s from Schedule s where s.professional = :professional and s.dateStart >= :start and s.dateEnd <= :end")
	List<Schedule> findByProfessionalAndDateStartAndDateEnd(Professional professional, Date start, Date end);

	List<Schedule> findByProfessionalAndDayOfWeekAndPatient(Professional professional, Integer dayOfWeek, Patient patient);

	Schedule findById(Long id);
}