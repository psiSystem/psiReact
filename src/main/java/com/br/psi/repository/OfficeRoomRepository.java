package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.OfficeRoom;

public interface OfficeRoomRepository extends JpaRepository<OfficeRoom, String> {
}