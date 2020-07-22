package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;
import com.br.psi.model.PlanHeathClient;

public interface PlanHeathClientRepository extends JpaRepository<PlanHeathClient, String> {

	List<PlanHeathClient> findAllByClient(Client client);
}