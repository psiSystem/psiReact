package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.DayWeek;
import com.br.psi.model.Shifts;

public interface ShiftsRepository extends JpaRepository<Shifts, String> {
}