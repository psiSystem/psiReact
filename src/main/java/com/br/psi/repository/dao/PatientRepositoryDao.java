package com.br.psi.repository.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.br.psi.model.Client;
import com.br.psi.model.Patient;
import com.br.psi.repository.PatientRepositoryService;

@Repository("patientRepositoryService")
public class PatientRepositoryDao implements PatientRepositoryService, Serializable {
	private static final long serialVersionUID = 1L;
	
	 @PersistenceContext
	 protected EntityManager entityManager;

	@Override
	public List<Patient> findByPersonClient(Client client) {
		return entityManager.createQuery("from Patient p where p.person.client =:client ").setParameter("client", client).getResultList();
	}

	@Override
	public Patient findById(Long id) {
		return (Patient) entityManager.createQuery("from Patient p where p.id =:id ").setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> findByPersonClientAndPatient(Client client, Patient patient) {
		 List<Patient> resultList = (List<Patient>) entityManager.createNativeQuery("select p.* from patient p "
					+ " inner join person pe on pe.id = p.person_id"
					+ " inner join person_client cp on cp.person_id = pe.id"
					+ " where pe.name like :name and cp.client_id =:client",Patient.class).setParameter("client", client.getId()).setParameter("name", "%"+patient.getPerson().getName()+"%")
					.getResultList();
		return resultList;
	}
	
	
}
