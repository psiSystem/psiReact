package com.br.psi.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.psi.repository.PersonRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(personRepository == null) {
			return true;
		}
		return value != null && personRepository.findByEmail(value) == null; 
	}
	
}