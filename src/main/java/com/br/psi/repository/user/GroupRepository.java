package com.br.psi.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.user.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}