package com.thamirisoc.AppContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.thamirisoc.AppContatos.controller.BadRequestPersonalizada;
import com.thamirisoc.AppContatos.model.Contatos;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.model.TipoContato;
import com.thamirisoc.AppContatos.repository.ContatosRepository;
import com.thamirisoc.AppContatos.repository.PessoasRepository;

@RestController
public class ContatosService {

	@Autowired
	private ContatosRepository contatoRepository;
	
	@Autowired
	private PessoasRepository pessoaRepository;
	
	public Contatos criar(Contatos contato) {
	    
		if(contato == null) {
			throw new BadRequestPersonalizada("Contato não pode ser nulo");
		}
		if(contato.getContato() == null || contato.getContato().trim().isEmpty()) {
			throw new BadRequestPersonalizada("Contato não pode ser nulo ou vazio");
		}
		if(contato.getTipoContato() == null) {
			throw new BadRequestPersonalizada("Tipo de contato não pode ser nulo!");
		}
		
		if (contato.getPessoa() == null || contato.getPessoa().getId() == null) {
			throw new BadRequestPersonalizada("Pessoa não encontrada!");
	    }
		
	    Optional<Pessoas> buscarPessoaPorId = pessoaRepository.findById(contato.getPessoa().getId());
	    if (buscarPessoaPorId.isEmpty()) {
	    	throw new BadRequestPersonalizada("Pessoa não encontrada!");
	    }	    
	    if (contato.getTipoContato() == TipoContato.EMAIL && !ValidacoesDeDados.validandoEmail(contato.getContato())) {
	    	throw new BadRequestPersonalizada("Email inválido!");
        }      
        if (contato.getTipoContato() == TipoContato.URL && !ValidacoesDeDados.validandoUrl(contato.getContato())) {
        	throw new BadRequestPersonalizada("URL inválida!");
        }        
       
        contato.setPessoa(buscarPessoaPorId.get());
	    return contatoRepository.save(contato);
	}	    
	
	public Optional<Contatos> buscarId(Long id) {
		return contatoRepository.findById(id);
	}
	
	public List<Contatos> buscarContatosPorPessoa(Long pessoaId) {
        return contatoRepository.findByPessoaId(pessoaId);
    }
		
	public Contatos editar(Long id, Contatos contato) {
		Optional<Contatos> buscarContato = contatoRepository.findById(id);
		
		if(contato.getContato() == null || contato.getContato().trim().isEmpty()) {
			throw new BadRequestPersonalizada("Contato não pode ser nulo e nem vazio");			
		}
		if(buscarContato.isPresent()) {
			Contatos contatoAtualizado = buscarContato.get();
			contatoAtualizado.setTipoContato(contato.getTipoContato());
			contatoAtualizado.setContato(contato.getContato());			
			return contatoRepository.save(contatoAtualizado);
		}else {
			return contatoRepository.save(contato);
		}		
	}
	
	public void deletar(Long id) {		
		if (!contatoRepository.existsById(id)) {
	        throw new BadRequestPersonalizada("ID " + id + " não encontrado.");
	    }		
	    contatoRepository.deleteById(id);				
	}
	
}