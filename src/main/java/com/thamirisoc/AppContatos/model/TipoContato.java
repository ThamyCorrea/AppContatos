package com.thamirisoc.AppContatos.model;

public enum TipoContato {
	
	TELEFONE(0),
	CELULAR(1);
	
	private final int codigoContato;
	
	TipoContato(int codigoContato){
		this.codigoContato = codigoContato;
	}
	
	public int getCodigoContato(){
		return codigoContato;
	}
	
	public static TipoContato fromCodigoContato(int codigoContato) {
		
		try {
			
			for(TipoContato tipo : TipoContato.values()) {
				if(tipo.getCodigoContato() == codigoContato) {
					return tipo;
				}
			}
		}catch(IllegalArgumentException e) {
			System.err.println("Código inválido! Escolha 0 para Telefone e 1 para Celular");
		}
		return null;
	}	
	
}	


