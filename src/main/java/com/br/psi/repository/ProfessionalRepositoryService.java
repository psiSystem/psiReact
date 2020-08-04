package com.br.psi.repository;

import java.util.List;

import com.br.psi.model.Client;
import com.br.psi.model.Person;
import com.br.psi.model.Professional;

public interface ProfessionalRepositoryService  {

	List<Professional> findByPersonClient(Client client);

	Professional findByPerson(Person person);
	
	List<Professional> findByPersonClientAndPersonName(Client client, String name);


}