package com.br.psi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByName(String name);

}