package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Anddress;
import com.br.psi.model.FilePatient;
import com.br.psi.model.FileProfessional;
import com.br.psi.model.Professional;

public interface FileProfessionalRepository extends JpaRepository<FileProfessional, String> {

	List<FileProfessional> findAllByProfessional(Professional professional);
}