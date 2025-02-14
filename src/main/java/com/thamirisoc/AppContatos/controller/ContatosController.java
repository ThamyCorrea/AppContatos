package com.thamirisoc.AppContatos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamirisoc.AppContatos.model.Contatos;
import com.thamirisoc.AppContatos.service.ContatosService;

@RestController
@RequestMapping("/api/contato")
public class ContatosController {	
	
	@Autowired
	private ContatosService contatoService;
	
	@PostMapping
	public ResponseEntity<List<Contatos>> criarContato(@RequestBody List<Contatos> contatos){        
	    List<Contatos> contatosCriados = new ArrayList<>();

	    for (Contatos contato : contatos) {  
	        Contatos contatoCriado = contatoService.criarContato(contato);
	        if (contatoCriado != null) {
	            contatosCriados.add(contatoCriado);
	        }
	    }

	    if (contatosCriados.isEmpty()) {
	        return ResponseEntity.badRequest().build();
	    }

	    return ResponseEntity.ok(contatosCriados);
	}
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Optional<Contatos>> buscarPorIdPessoa(@PathVariable Long id){
		Optional<Contatos> buscarId = contatoService.buscarContatosPorPessoa(id);
		if(buscarId.isEmpty()) {
			return ResponseEntity.badRequest().build();
		
		} else{
			return ResponseEntity.ok(buscarId);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contatos>> buscarPorIdContato(@PathVariable Long id){
		Optional<Contatos> buscarId = contatoService.buscarId(id);
		if(buscarId.isEmpty()) {
			return ResponseEntity.badRequest().build();
		
		} else{
			return ResponseEntity.ok(buscarId);
		}
		
	}

}
