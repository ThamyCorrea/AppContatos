package com.thamirisoc.AppContatos.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.thamirisoc.AppContatos.model.Contatos;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.repository.ContatosRepository;
import com.thamirisoc.AppContatos.repository.PessoasRepository;


@RestController
public class ContatosService {

	@Autowired
	private ContatosRepository contatoRepository;
	
	@Autowired
	private PessoasRepository pessoaRepository;
	
	public Contatos criarContato(Contatos contato) {
	    if (contato.getPessoa() == null || contato.getPessoa().getId() == null) {
	        System.out.println("Pessoa não encontrada");
	        return null;
	    }

	    Optional<Pessoas> buscarPessoa = pessoaRepository.findById(contato.getPessoa().getId());
	    if (buscarPessoa.isEmpty()) {
	        System.out.println("Pessoa não encontrada!");
	        return null;
	    }

	    contato.setPessoa(buscarPessoa.get());
	    return contatoRepository.save(contato);
	}

	    
	
	public Optional<Contatos> buscarId(Long id) {
		return contatoRepository.findById(id);
	}
	
	public Optional<Contatos> buscarContatosPorPessoa(Long pessoaId) {
	    return contatoRepository.findById(pessoaId);
	}

		
	public Contatos editarContato(Contatos contato) {
		Optional<Contatos> buscarContato = contatoRepository
				.findById(contato.getId());
		if(buscarContato.isPresent()) {
			Contatos contatoAtualizado = buscarContato.get();
			contatoAtualizado.setTipoContato(contato.getTipoContato());
			contatoAtualizado.setContatoTelefonico(contato.getContatoTelefonico());
			
			return contatoRepository.save(contatoAtualizado);
		}else {
			return contatoRepository.save(contato);
		}
		
	}
	
	public void deletarContato(Long id){
		contatoRepository.deleteById(id);
	}
}