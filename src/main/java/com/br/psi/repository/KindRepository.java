package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Kind;

public interface KindRepository extends JpaRepository<Kind, String> {
	
}