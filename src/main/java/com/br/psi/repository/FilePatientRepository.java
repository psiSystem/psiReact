package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Anddress;
import com.br.psi.model.Client;
import com.br.psi.model.FilePatient;
import com.br.psi.model.FileProfessional;
import com.br.psi.model.Patient;

public interface FilePatientRepository extends JpaRepository<FilePatient, String> {

	List<FilePatient> findAllByPatientPersonClient(Client client);

	List<FilePatient> findAllByPatient(Patient patient);
}