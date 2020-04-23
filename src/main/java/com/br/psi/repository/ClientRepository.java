package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
}