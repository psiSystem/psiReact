package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;
import com.br.psi.model.Patient;

public interface PatientRepositoryService  {

	List<Patient> findByPersonClient(Client client);

	Patient findById(Long id);

	List<Patient> findByPersonClientAndPatient(Client client, Patient patient);
}