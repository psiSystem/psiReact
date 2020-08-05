package com.br.psi.repository.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.br.psi.model.Client;
import com.br.psi.model.OfficeRoom;
import com.br.psi.model.Patient;
import com.br.psi.model.Professional;
import com.br.psi.model.Shifts;
import com.br.psi.repository.PatientRepositoryService;
import com.br.psi.repository.ShiftsRepositoryService;

@Repository("shiftsRepositoryService")
public class ShiftsRepositoryDao implements ShiftsRepositoryService, Serializable {
	private static final long serialVersionUID = 1L;
	
	
	 @PersistenceContext
	 protected EntityManager entityManager;



	@Override
	public Boolean findByTimeAvailable(Integer dayOfWeek, Professional professional, LocalDateTime localStart,LocalDateTime localEned, OfficeRoom officeRoom) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		 Integer result =  (Integer) entityManager.createNativeQuery("select (DATEDIFF(MINUTE,:datestart,:dateend) - SUM(DATEDIFF(MINUTE,date_start,date_end))) / 30"
					+ " from schedule "
					+ " where (professional_id = :professional or office_room_id = :office)"
					+ " and day_of_week = :dayofweek "
					+ " and date_start >= :datestart "
					+ " and date_end <= :dateend "
					)
				 .setParameter("professional", professional != null ? professional.getId() : null)
				 .setParameter("office", officeRoom.getId())
				 .setParameter("dayofweek", dayOfWeek)
				 .setParameter("datestart", localStart)
				 .setParameter("dateend", localEned)
					.getSingleResult();
		 
		return result == null || result > 1;
	}
	
	
}
