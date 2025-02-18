package com.thamirisoc.AppContatos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestPersonalizada extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public BadRequestPersonalizada(String mensagem) {
		System.out.print(mensagem);
	}

}


