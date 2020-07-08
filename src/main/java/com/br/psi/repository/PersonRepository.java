package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

	Person findByEmail(String value);

	Person findByCpf(String value);
}