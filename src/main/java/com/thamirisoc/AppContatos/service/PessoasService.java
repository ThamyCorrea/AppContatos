package com.thamirisoc.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamirisoc.AppContatos.model.MalaDireta;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.repository.PessoasRepository;
import com.thamirisoc.AppContatos.controller.BadRequestPersonalizada;

@Service
public class PessoasService {
	
	@Autowired
	private PessoasRepository pessoasRepository;	
		
	public Pessoas criar(Pessoas pessoa) {		
				
		if(pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
			throw new BadRequestPersonalizada("Nome não pode ser nulo e nem vazio \n");			
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
	
	
	public List<Pessoas> listar(){
		return pessoasRepository.findAll();
	}

	public Pessoas editar(Long id, Pessoas pessoa) {
	    try {
	        Optional<Pessoas> encontrarPessoa = pessoasRepository.findById(id);
	        
	        if(pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
				throw new BadRequestPersonalizada("Nome não pode ser nulo e nem vazio \n");			
			}
	        if (encontrarPessoa.isPresent()) {
	            Pessoas pessoaEditada = encontrarPessoa.get();
	            pessoaEditada.setNome(pessoa.getNome());
	            pessoaEditada.setEndereco(pessoa.getEndereco());
	            pessoaEditada.setCep(pessoa.getCep());
	            pessoaEditada.setCidade(pessoa.getCidade());
	            pessoaEditada.setUf(pessoa.getUf());

	            return pessoasRepository.save(pessoaEditada);
	        }
	        
	        return pessoasRepository.save(pessoa);
	        
	    } catch (Exception e) {
	    	throw new RuntimeException("Erro ao editar pessoa: " + e.getMessage(), e);
	    }
	}

	public void deletar(Long id) {		
		if (!pessoasRepository.existsById(id)) {
	        throw new BadRequestPersonalizada("ID " + id + " não encontrado.");
	    }		
	    pessoasRepository.deleteById(id);
				
	}
}

