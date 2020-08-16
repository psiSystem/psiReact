package com.br.psi.dto;

import com.br.psi.validation.UniqueCpf;
import com.br.psi.validation.UniqueEmail;

public class ValidateCpfAndEmail {

		@UniqueCpf(message = "{cpf.cadastrado}")
		private String cpf;
		
		@UniqueEmail(message = "{email.cadastrado}")
		private String email;

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		
}
