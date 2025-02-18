package com.thamirisoc.AppContatos.model;

import com.thamirisoc.AppContatos.controller.BadRequestPersonalizada;

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
    
    public static boolean codigoValido(int codigo) {
        for (TipoContato tipo : TipoContato.values()) {
            if (tipo.getCodigoContato() == codigo) {
                return true;
            }
        }
        return false;
    }

    public static TipoContato fromCodigoContato(int codigoContato) {
    	switch (codigoContato) {
        case 0: return TELEFONE;
        case 1: return CELULAR;
        case 2: return EMAIL;
        case 3: return URL;
        default:
            throw new BadRequestPersonalizada("Código inválido! Escolha entre: 0 (Telefone), 1 (Celular), 2 (E-mail) ou 3 (URL)");
            /*Não tive tempo hábil para conseguir fazer chamar o throw, mesmo chamando no serviço para validação. Irá ficar como uma melhoria futura*/
    	}
    }
}
