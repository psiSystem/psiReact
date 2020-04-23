package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.CameClinic;

public interface CameClinicRepository extends JpaRepository<CameClinic, String> {
}