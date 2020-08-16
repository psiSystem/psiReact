package com.br.psi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.psi.repository.PersonRepository;

public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (personRepository == null) {
			return true;
		}
		return value != null && personRepository.findByCpf(value) == null;

	}

}