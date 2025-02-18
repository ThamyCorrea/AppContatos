package com.thamirisoc.AppContatos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamirisoc.AppContatos.model.Contatos;
import com.thamirisoc.AppContatos.service.ContatosService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contato")
public class ContatosController {	
	
	@Autowired
	private ContatosService contatoService;
	
	@Operation(summary = "Editar contato. Observação: No Request body, excluir ID e dados da pessoa e retirar ',' no final do contato")
	@PostMapping
	public ResponseEntity<List<Contatos>> criar(@RequestBody List<Contatos> contatos){        
	    List<Contatos> contatosCriados = new ArrayList<>();
	    
	    for (Contatos contato : contatos) {  
	        Contatos contatoCriado = contatoService.criar(contato);
	        
	        if (contatoCriado != null) {
	            contatosCriados.add(contatoCriado);
	        }
	    }
	    if (contatosCriados.isEmpty()) {
	        throw new BadRequestPersonalizada("Nenhum contato criado");
	    }
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(contatosCriados);
	}
	
	@Operation(summary = "Listar todos os contatos pelo ID da pessoa")
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<Contatos>> buscarPorIdPessoa(@PathVariable Long id){
		List<Contatos> buscarId = contatoService.buscarContatosPorPessoa(id);
		
		if(buscarId.isEmpty()) {
			return ResponseEntity.badRequest().build();		
		} else{
			return ResponseEntity.ok(buscarId);
		}		
	}	
	
	@Operation(summary = "Buscar contato por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contatos>> buscarId(@PathVariable Long id){
		Optional<Contatos> buscarId = contatoService.buscarId(id);
		
		if(buscarId.isEmpty()) {
			return ResponseEntity.badRequest().build();		
		} else{
			return ResponseEntity.ok(buscarId);
		}		
	}
	
	@Operation(summary = "Editar contato. Observação: No Request body, excluir ID e dados da pessoa e retirar ',' no final do contato")
	@PutMapping("/{id}")
	public ResponseEntity <Contatos> editar(@PathVariable Long id, @RequestBody Contatos contato){
		Contatos editarContato = contatoService.editar(id, contato);
		
		if(editarContato == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(editarContato);
		}
	}
	
	@Operation(summary = "Deletar contato por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
	        contatoService.deletar(id);
	        return ResponseEntity.noContent().build(); // 204 - No Content
	    } catch (BadRequestPersonalizada e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }	
	}

}
