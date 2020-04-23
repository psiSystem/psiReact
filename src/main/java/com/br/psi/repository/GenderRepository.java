package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Gender;

public interface GenderRepository extends JpaRepository<Gender, String> {
}