package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.psi.model.Client;
import com.br.psi.model.Patient;
import com.br.psi.model.PaymentPatient;

public interface PaymentPatientRepositoryService  {
	
	List<PaymentPatient> findByPatientPersonClient(Client client, PaymentPatient paymentPatient);
	
}