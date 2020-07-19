package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Status;

public interface StatusRepository extends JpaRepository<Status, String> {
}