package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Formation;

public interface GroupSpecialtyRepository extends JpaRepository<Formation, String> {
}