package com.br.psi.repository.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

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
				+ " inner join person_client cp on cp.person_id = pe.id"
				+ " where pe.name like :name and cp.client_id =:client",Professional.class).setParameter("client", client.getId()).setParameter("name", "%"+name+"%")
				.getResultList();
		return resultList; 
	}

	
}
