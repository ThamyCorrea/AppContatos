package com.thamirisoc.AppContatos.model;


public record MalaDireta (Long id,
						  String nome,
						  String endereco, 
						  String cep, 
						  String cidade, 
						  String uf) {
	
	public String malaDiretaFormatada() {
		return endereco + " - CEP: " + cep + " - " + cidade + "/" + uf;
	}

	
}

	