package com.thamirisoc.AppContatos.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamirisoc.AppContatos.model.MalaDireta;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.repository.PessoasRepository;

@Service
public class PessoasService {
	
	@Autowired
	PessoasRepository pessoasRepository;
	
	
		
	public Pessoas criarPessoa(Pessoas pessoa) {
		
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
	
	
	public Optional<Pessoas> buscarId(Long id){
		return pessoasRepository.findById(id);
	}
	
	public Optional<MalaDireta> buscarIDMaladireta(Long id) {
	    Optional<Pessoas> pessoa = pessoasRepository.findById(id);
	    
	    if (pessoa.isPresent()) {  
	        Pessoas pessoaPresente = pessoa.get();  
	        
	        MalaDireta malaDireta = new MalaDireta(
	            pessoaPresente.getId(),
	            pessoaPresente.getNome(),
	            pessoaPresente.getEndereco(),
	            pessoaPresente.getCep(), 
	            pessoaPresente.getCidade(), 
	            pessoaPresente.getUf() 
	        );
	        
	        return Optional.of(malaDireta); 
	    }
	    
	    return Optional.empty();
	}
	
	public List<Pessoas> listarPessoas(){
		return pessoasRepository.findAll();
	}

	public Pessoas editar(Pessoas pessoa) {
		Optional<Pessoas> encontrarPessoa = pessoasRepository.findById(pessoa.getId());
		if(encontrarPessoa.isPresent()) {
			Pessoas pessoaEditada = encontrarPessoa.get();
			pessoaEditada.setNome(pessoa.getNome());
			pessoaEditada.setEndereco(pessoa.getEndereco());
			pessoaEditada.setCep(pessoa.getCep());
			pessoaEditada.setCidade(pessoa.getCidade());
			pessoaEditada.setUf(pessoa.getUf());
			
			return pessoasRepository.save(pessoaEditada);
		}
		return pessoasRepository.save(pessoa);
	}
		
}

