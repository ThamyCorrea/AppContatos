package com.thamirisoc.AppContatos.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.service.PessoasService;

@RestController
@RequestMapping("api/pessoas")
public class PessoasController {

	@Autowired
	PessoasService pessoaService;
	
	@PostMapping
	public ResponseEntity<Pessoas> save(@RequestBody Pessoas pessoa){
		Pessoas newPessoa = pessoaService.save(pessoa);
		
		if(newPessoa == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(newPessoa);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoas>> findById(@PathVariable long id){
		Optional<Pessoas> pessoa = pessoaService.findById(id);
		if(pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(pessoa);
		}
	}
	
	
	
	
}
