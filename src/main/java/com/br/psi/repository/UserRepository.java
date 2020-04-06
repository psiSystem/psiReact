package com.br.psi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	void deleteById(Long id);
	Optional<User> findById(Long id);
	User findByUserName(String userName);
}