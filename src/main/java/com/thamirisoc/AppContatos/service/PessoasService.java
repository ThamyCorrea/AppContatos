package com.thamirisoc.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thamirisoc.AppContatos.model.Contatos;
import com.thamirisoc.AppContatos.model.MalaDireta;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.repository.ContatosRepository;
import com.thamirisoc.AppContatos.repository.PessoasRepository;

import jakarta.transaction.Transactional;

import com.thamirisoc.AppContatos.controller.BadRequestPersonalizada;

@Service
public class PessoasService {
	
	@Autowired
	private PessoasRepository pessoasRepository;
	
	@Autowired
	private ContatosRepository contatoRepository;
	
		
	public Pessoas criar(Pessoas pessoa) {		
				
		if(pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
			throw new BadRequestPersonalizada("Nome não pode ser nulo e nem vazio");			
		}
		
		if(pessoa.getUf().length() != 2 || !pessoa.getUf().matches("[A-Z]{2}") ) {
			throw new BadRequestPersonalizada("UF precisa ter 2 letras maiúsculas");
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

	@Transactional
	public void deletar(Long pessoaId) {		
		if (!pessoasRepository.existsById(pessoaId)) {
	        throw new BadRequestPersonalizada("ID " + pessoaId + " não encontrado.");
	    }		
		List<Contatos> contatos = contatoRepository.findByPessoaId(pessoaId);
		
		for(Contatos contato : contatos) {
			contatoRepository.delete(contato);
		}
		pessoasRepository.deleteById(pessoaId);						
	}
}

