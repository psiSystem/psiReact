package com.br.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.psi.model.DayWeek;
import com.br.psi.model.Schedule;
import com.br.psi.model.Shifts;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
}