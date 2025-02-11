package com.thamirisoc.AppContatos.model;


public class MalaDireta {
	
	private String endereco;		
	private Integer cep;
	private String cidade;
	private String uf;
	
	
	@Override
	public String toString() {
		return    "MalaDireta [Rua " + endereco 
				+ " - CEP: "         + cep      
				+ " - "              + cidade   
				+ "/"                + uf       
				+ "]" 
				;}
	
	
}

	