package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.GroupSpecialty;
import com.br.psi.model.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, String> {
	List<Specialty> findByGroupSpecialty(GroupSpecialty groupSpecialty);
	
	
}