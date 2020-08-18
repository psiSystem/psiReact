package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	Patient findAllById(Long patient);

}