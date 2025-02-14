package com.thamirisoc.AppContatos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Pessoas")
public class Pessoas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String nome;
	
	@Column(nullable = true, length = 150)
	private String endereco;
	
	@Column(nullable = true, length = 10)
	private String cep;
	
	@Column(nullable = true, length = 100)
	private String cidade;
	
	@Column(nullable = true, length = 2)
	private String uf;
	
	@OneToMany(mappedBy = "pessoa")
	@JsonManagedReference
    private List<Contatos> contatos;
	
	public Pessoas() {
		
	}

	public Pessoas(Long id, String nome, String endereco, String cep, String cidade, String uf,
			List<Contatos> contatos) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.contatos = contatos;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	

	public List<Contatos> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contatos> contatos) {
		this.contatos = contatos;
	}

	@Override
	public String toString() {
		return "Pessoas [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cep=" + cep + ", cidade=" + cidade
				+ ", uf=" + uf + "]";
	}

	
	
	
	
	

}
