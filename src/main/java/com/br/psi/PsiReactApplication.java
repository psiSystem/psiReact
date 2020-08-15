package com.br.psi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class PsiReactApplication{

		
	public static void main(String[] args) {
		SpringApplication.run(PsiReactApplication.class, args);
	}
	
	@GetMapping("/")
	public String home() {
		return "/index.html";
	}
	
	@GetMapping("/index")
	public String index() {
		return "/index.html";
	}
}
