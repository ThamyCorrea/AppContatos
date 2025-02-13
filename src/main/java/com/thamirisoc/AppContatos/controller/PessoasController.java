package com.thamirisoc.AppContatos.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thamirisoc.AppContatos.model.MalaDireta;
import com.thamirisoc.AppContatos.model.Pessoas;
import com.thamirisoc.AppContatos.service.PessoasService;

@RestController
@RequestMapping("api/pessoas")
public class PessoasController {

	@Autowired
	PessoasService pessoaService;
	
	@PostMapping
	public ResponseEntity<Pessoas> CriarPessoa(@RequestBody Pessoas pessoa){
		Pessoas newPessoa = pessoaService.criarPessoa(pessoa);
		
		if(newPessoa == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(newPessoa);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoas>> listarPessoas(){
		List<Pessoas> pessoas = pessoaService.listarPessoas();
		if(pessoas == null)
			return ResponseEntity.badRequest().build();
		if(pessoas.size() == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoas>> buscarPessoaPorId(@PathVariable long id){
		Optional<Pessoas> pessoa = pessoaService.buscarId(id);
		if(pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(pessoa);
		}
	}
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
	        return ResponseEntity.notFound().build();
	    }
	}

	
	
	
}
