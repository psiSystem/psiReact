package com.br.psi.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.user.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
    List<Group> findAllByUserId(String id);
}