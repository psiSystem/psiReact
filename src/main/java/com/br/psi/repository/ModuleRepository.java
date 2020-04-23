package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Module;

public interface ModuleRepository extends JpaRepository<Module, String> {
}