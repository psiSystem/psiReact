package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.DayWeek;

public interface DayWeekRepository extends JpaRepository<DayWeek, String> {
}