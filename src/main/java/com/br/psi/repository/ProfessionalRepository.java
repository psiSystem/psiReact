package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, String> {
}