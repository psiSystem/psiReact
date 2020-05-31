package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;
import com.br.psi.model.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, String> {

	List<Professional> findByPersonClient(Client client);

}