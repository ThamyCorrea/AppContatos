package com.thamirisoc.AppContatos.model;

public enum TipoContato {
    
    TELEFONE(0),
    CELULAR(1),
    EMAIL(2),
    URL(3);

    private final int codigoContato;
    
    
    TipoContato(int codigoContato) {
        this.codigoContato = codigoContato;
        
    }

    public int getCodigoContato() {
        return codigoContato;
    }

    public static TipoContato fromCodigoContato(int codigoContato) {
        for (TipoContato tipo : TipoContato.values()) {
            if (tipo.getCodigoContato() == codigoContato) {
                return tipo;
            }
        }
        
        throw new IllegalArgumentException("Código inválido! "
        									+ "Escolha 0 para Telefone, "
        									+ "1 para Celular, "
        									+ "2 para E-mail e "
        									+ "3 para URL."); 
    }
}
