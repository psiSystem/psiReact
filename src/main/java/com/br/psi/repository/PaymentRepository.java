package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}