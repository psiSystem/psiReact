package com.br.psi.repository.dao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.br.psi.dto.FinanceProfessional;
import com.br.psi.model.Client;
import com.br.psi.model.Person;
import com.br.psi.model.Professional;
import com.br.psi.repository.ProfessionalRepositoryService;

@Repository("professionalRepositoryService")
public class ProfessionalRepositoryDao implements ProfessionalRepositoryService, Serializable {
	private static final long serialVersionUID = 1L;
	
	 @PersistenceContext
	 protected EntityManager entityManager;
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Professional> findByPersonClient(Client client) {

		return entityManager.createQuery("from Professional p where p.person.client =:client ").setParameter("client", client).getResultList();
	}

	@Override
	public Professional findByPerson(Person person) {
		
		return (Professional) entityManager.createQuery("from Professional p where p.person =:person ").setParameter("person", person).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Professional> findByPersonClientAndPersonName(Client client, String name) {
		List<Professional>  resultList = (List<Professional>) entityManager.createNativeQuery("select p.* from professional p "
				+ " inner join person pe on pe.id = p.person_id"
				+ " where pe.name like :name and pe.client_id =:client",Professional.class).setParameter("client", client.getId()).setParameter("name", "%"+name+"%")
				.getResultList();
		return resultList; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceProfessional> findAllByFinanceProfessional(FinanceProfessional financeProfessional) {
		
		List<FinanceProfessional>  resultList = entityManager.createNativeQuery(
				" SELECT count(sc.id) AS amount,\r\n" + 
				"       count(sc.id) * isnull((pp.value / pp.amount),phc.value) AS grossAmount,\r\n" + 
				"       pr.name professionalName,\r\n" + 
				"       prt.name AS patientName,\r\n" + 
				"       ph.description planHeath,\r\n" + 
				"       pay.name AS payment,\r\n" + 
				"	   isnull(phc.value, pp.value / pp.amount) valuePlan,\r\n" + 
				"       min(sc.date_start) dateStart,\r\n" + 
				"       max(sc.date_end) dateEnd\r\n" + 
				"FROM professional p\r\n" + 
				"INNER JOIN person pr ON pr.id = p.person_id\r\n" + 
				"INNER JOIN schedule sc ON sc.professional_id = p.id\r\n" + 
				"INNER JOIN payment_patient pp ON pp.id = sc.payment_patient_id \r\n" + 
				"INNER JOIN patient pt ON pt.id = pp.patient_id\r\n" + 
				"INNER JOIN person prt ON prt.id = pt.person_id\r\n" + 
				"LEFT JOIN plan_heath ph ON ph.id = pp.plan_health_id\r\n" + 
				"left join  plan_heath_client phc on phc.plan_heath_id = pp.plan_health_id\r\n" + 
				"AND phc.client_id = pr.client_id\r\n" + 
				"AND phc.formation_id = p.formation_id\r\n" + 
				"and phc.plan_code_id = sc.plan_code_id\r\n" + 
				"INNER JOIN payment pay ON pay.id = pp.payment_id\r\n" + 
				"WHERE sc.date_start <= GETDATE()\r\n" + 
				"and pr.client_id = isnull(:client,pr.client_id)\r\n" + 
				"and p.id = isnull(:professional,p.id)\r\n" + 
				"and sc.date_start >= isnull(:dateStart,sc.date_start)\r\n" + 
				"and sc.date_start <= :dateEnd\r\n" + 
				"GROUP BY prt.name,\r\n" + 
				"         pr.name,\r\n" + 
				"         pp.value,\r\n" + 
				"         pp.amount,\r\n" + 
				"         ph.description,\r\n" + 
				"         pay.name,\r\n" + 
				"         phc.value")
				.setParameter("client", financeProfessional.getClient().getId())
				.setParameter("professional", financeProfessional.getProfessional() != null ? financeProfessional.getProfessional().getId() : null)
				.setParameter("dateStart", financeProfessional.getDateStart() != null ? financeProfessional.getDateStart() : LocalDateTime.now().plusDays(-90))
				.setParameter("dateEnd", financeProfessional.getDateEnd() != null ? financeProfessional.getDateEnd() : new Date())
				.unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(Transformers.aliasToBean(FinanceProfessional.class))
				.getResultList();
		return resultList; 
	}

	
}
