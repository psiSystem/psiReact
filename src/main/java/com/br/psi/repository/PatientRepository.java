package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;
import com.br.psi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	List<Patient> findByPersonClient(Client client);
}