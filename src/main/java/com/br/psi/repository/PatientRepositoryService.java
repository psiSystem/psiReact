package com.br.psi.repository;

import java.util.List;

import com.br.psi.dto.PatientDto;
import com.br.psi.model.Client;
import com.br.psi.model.Formation;
import com.br.psi.model.Patient;
import com.br.psi.model.PaymentPatient;

public interface PatientRepositoryService  {

	List<PatientDto> findByPersonClient(Client client);

	Patient findById(Long id);

	List<PatientDto> findByPersonClientAndPatient(Client client, Patient patient);

	List<PaymentPatient> findPaymentPatient(Patient patient, Formation formation);

	Patient save(Patient patient);
}