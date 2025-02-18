package com.thamirisoc.AppContatos.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

import com.thamirisoc.AppContatos.model.MalaDireta;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.service.PessoasService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("api/pessoas")
public class PessoasController {

	@Autowired
	private PessoasService pessoaService;
	
	@Operation(summary = "Criar pessoa. Observação: No Request body, excluir ID e dados do contatos e retirar ',' no final da UF")
	@PostMapping
	public ResponseEntity<Pessoas> Criar(@RequestBody Pessoas pessoa){
		Pessoas newPessoa = pessoaService.criar(pessoa);	
		
		if(newPessoa.getNome() == null || newPessoa.getNome().trim().isEmpty()) {
			throw new BadRequestPersonalizada("Nome não pode ser nulo e nem vazio");			
		}		
		else {
			return ResponseEntity.status(201).body(newPessoa);
		}
	}
	
	@Operation(summary = "Listar todas as pessoas")
	@GetMapping
	public ResponseEntity<List<Pessoas>> listar(){
		List<Pessoas> pessoas = pessoaService.listar();
		
		if(pessoas == null)
			return ResponseEntity.badRequest().build();
		if(pessoas.size() == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoas);
	}
	
	@Operation(summary = "Listar pessoa por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoas>> buscarId(@PathVariable Long id){
		Optional<Pessoas> pessoa = pessoaService.buscarId(id);
		
		if(pessoa.isEmpty()) {
			throw new BadRequestPersonalizada("ID " + id + " não existe ");
		}else {
			return ResponseEntity.ok(pessoa);
		}
	}
	
	@Operation(summary = "Listar pessoa por ID utilizando Mala Direta")
	@GetMapping("/malaDireta/{id}")
	public ResponseEntity<Map<String, String>> buscarMalaDireta(@PathVariable Long id) {
	    Optional<MalaDireta> resultado = pessoaService.buscarIDMaladireta(id);
	    
	    if (resultado.isPresent()) {            
	        MalaDireta malaDireta = resultado.get();  
	        Map<String, String> jsonFormatado = new LinkedHashMap<>();
	        jsonFormatado.put("ID", String.valueOf(malaDireta.id()));
	        jsonFormatado.put("Nome", malaDireta.nome());
	        jsonFormatado.put("MalaDireta", malaDireta.endereco() + " - CEP: " + malaDireta.cep() + " - " + malaDireta.cidade() + "/" + malaDireta.uf());

	        return ResponseEntity.ok(jsonFormatado);
	    } else {
	    	throw new BadRequestPersonalizada("ID não existe");
	    }
	}
	
	@Operation(summary = "Editar pessoa. Observação: No Request body, excluir ID e dados do contatos e retirar ',' no final da UF")
	@PutMapping("/{id}")
	public ResponseEntity<Pessoas> editar(@PathVariable Long id, @RequestBody Pessoas pessoa){		
		Pessoas pessoaEditada = pessoaService.editar(id, pessoa);	
		
		if(pessoaEditada.getId() != id) {
			throw new BadRequestPersonalizada("ID " + id + " não existe");
		}
		if(pessoaEditada.getNome() == null || pessoaEditada.getNome().trim().isEmpty()) {
			throw new BadRequestPersonalizada("Nome não pode ser nulo e nem vazio");			
		}	
		else {
			return ResponseEntity.status(202).body(pessoaEditada);
		}
	}	
	
	@Operation(summary = "Deletar pessoa por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		try {
	        pessoaService.deletar(id);
	        return ResponseEntity.noContent().build(); // 204 - No Content
	    } catch (BadRequestPersonalizada e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }	
	}
}
