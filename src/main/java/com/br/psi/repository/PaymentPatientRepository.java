package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.PaymentPatient;
import com.br.psi.model.User;

public interface PaymentPatientRepository extends JpaRepository<PaymentPatient, String> {
}