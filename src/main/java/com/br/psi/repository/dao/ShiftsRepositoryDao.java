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

import com.br.psi.dto.FilterCalendar;
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
	public List<Object> findByTimeAvailable(FilterCalendar filter, Client client) {
		List<Object> Shifts = entityManager.createNativeQuery("select s.id,dw.day_of_week,isnull(time_free.dt_end,s.time_end) time_end,isnull(time_free.dt,s.time_start) time_start,s.professional_id,s.create_date ,isnull(time_free.available,1) time_available from shifts s\r\n" + 
				"inner join day_week dw on dw.id = s.day_week_id\r\n" + 
				"left join professional p on p.id = s.professional_id\r\n" + 
				"outer apply (\r\n" + 
				"select (((DATEDIFF(MINUTE,s.time_start,s.time_end))  - SUM(DATEDIFF(MINUTE,date_start,date_end))) / 30) available,\r\n" + 
				"		CONVERT(varchar(10),sc.date_start,120) +' ' + CONVERT(VARCHAR(8),S.time_start,108) dt,\r\n" + 
				"		CONVERT(VARCHAR(10),sc.date_end,120)  +' ' + CONVERT(VARCHAR(8),S.time_end,108) dt_end\r\n" + 
				"from schedule sc\r\n" + 
				"	where (professional_id = s.professional_id)\r\n" + 
				"	and day_of_week = dw.day_of_week	\r\n" +
				"	and sc.date_start > GETDATE()\r\n" + 
				"	and CONVERT(VARCHAR(10),sc.date_start,120) = CONVERT(VARCHAR(10),sc.date_end,120) "+
				"group by\r\n" + 
				"sc.professional_id,\r\n" + 
				"CONVERT(VARCHAR(10),sc.date_start,120),\r\n" + 
				"CONVERT(VARCHAR(10),sc.date_end,120),\r\n" + 
				"sc.day_of_week\r\n" + 
				") time_free\r\n" + 
				"where s.professional_id = isnull(:professional,s.professional_id)\r\n" +
				"and time_free.available = 0 "+
				"and p.formation_id = isnull(:formation,p.formation_id)")
			 .setParameter("professional", filter.getProfessional() != null && filter.getProfessional().getId() != null ? filter.getProfessional().getId() : null)
			 .setParameter("formation", filter.getFormation() != null && filter.getFormation().getId() != null? filter.getFormation().getId() : null)
				.getResultList();
		
		return Shifts;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Shifts> findByProfessionalAndProfessionalFormation(FilterCalendar filter, Client client) {
		List<Shifts> Shifts = entityManager.createNativeQuery("select s.* from shifts s\r\n" + 
				"inner join day_week dw on dw.id = s.day_week_id\r\n" + 
				" inner join office_room office on office.id = dw.office_room_id "+
				"left join professional p on p.id = s.professional_id\r\n" + 
				"where s.professional_id = isnull(:professional,s.professional_id)\r\n" + 
				"and p.formation_id = isnull(:formation,p.formation_id)"+
				" and office.client_id = :client",Shifts.class)
			 .setParameter("professional", filter.getProfessional() != null && filter.getProfessional().getId() != null ? filter.getProfessional().getId() : null)
			 .setParameter("formation", filter.getFormation() != null && filter.getFormation().getId() != null? filter.getFormation().getId() : null)
			 .setParameter("client", client.getId())
				.getResultList();
		
		return Shifts;
	}
	
	
}
