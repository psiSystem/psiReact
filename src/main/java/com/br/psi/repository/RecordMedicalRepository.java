package com.br.psi.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.psi.model.Patient;
import com.br.psi.model.Professional;
import com.br.psi.model.RecordMedical;

public interface RecordMedicalRepository extends JpaRepository<RecordMedical, Long>{

	List<RecordMedical> findAllByProfessional(@Valid Professional professional);
	@Query("select r from RecordMedical r where r.professional = :professional and r.patient = isnull(:patient,r.patient)")
	List<RecordMedical> findAllByProfessionalAndPatient(Professional professional, Patient patient);

}
