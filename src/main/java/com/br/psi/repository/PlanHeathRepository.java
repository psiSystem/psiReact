package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.PlanHeath;

public interface PlanHeathRepository extends JpaRepository<PlanHeath, String> {
}