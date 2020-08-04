package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.psi.model.Client;
import com.br.psi.model.Patient;
import com.br.psi.model.PaymentPatient;

public interface PaymentPatientRepository extends JpaRepository<PaymentPatient, String> {
	@Query("select p from PaymentPatient p where p.id = :id")
	List<PaymentPatient> findByPatientId(Long id);

	List<PaymentPatient> findByPatient(Patient patient);

	List<PaymentPatient> findByPatientPersonClient(Client client);
	
}