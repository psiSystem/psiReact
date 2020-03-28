package com.br.psi.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.user.User;

public interface UserRepository extends JpaRepository<User, String> {
}