package com.br.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Client;
import com.br.psi.model.OfficeRoom;

public interface OfficeRoomRepository extends JpaRepository<OfficeRoom, String> {

	List<OfficeRoom> findByClient(Client client);
}