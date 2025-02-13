package com.thamirisoc.AppContatos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.model.MalaDireta;
import com.thamirisoc.AppContatos.repository.PessoasRepository;

@Service
public class PessoasService {
	
	@Autowired
	PessoasRepository pessoasRepository;	
		
	public Pessoas save(Pessoas pessoa) {
		
		if(pessoa.getNome() == null) {
			System.out.println("Nome precisa ser preenchido");
			return null;
		}
		
		try {			
			return pessoasRepository.save(pessoa);
			
		}catch(Exception e) {
			System.err.println("Erro ao inserir dados pessoais!" +
							pessoa.toString() + ": " + e.getMessage());
			return null;
		}
		
	}
	
	
	public Optional<Pessoas> findById(Long id){
		return pessoasRepository.findById(id);
	}
	
	public List<MalaDireta> listarUsuarios() {
		List<MalaDireta> pessoas = new ArrayList<>();
        return pessoas;
    }
}

