package com.br.psi.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.psi.model.Client;
import com.br.psi.model.PlanHeath;

public interface PlanHeathRepository extends JpaRepository<PlanHeath, String> {

	@Query("select s from PlanHeath s")
	List<PlanHeath> findAll(Client client);
}