package com.thamirisoc.AppContatos.model;


public record MalaDireta (String id,
						  String nome,
						  String endereco, 
						  String cep, 
						  String cidade, 
						  String uf) {

	@Override
	public String toString() {
		return "id:" + id 
				+ "\n" 
				+ "nome:" + nome 
				+ "\n" 
				+ "MalaDireta: " 
				+ "endereco=" + endereco 
				+ ", cep=" + cep 
				+ ", cidade=" + cidade + ", uf=" + uf;
	}
	
	
	
}

	