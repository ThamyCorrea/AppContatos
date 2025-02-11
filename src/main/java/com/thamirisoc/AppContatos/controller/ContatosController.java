package com.thamirisoc.AppContatos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContatosController {	
	
	@GetMapping
	public String getApi() {
		return "Api java funcionando! 🚀";
	}

}
