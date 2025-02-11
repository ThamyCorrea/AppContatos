package com.thamirisoc.AppContatos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Contatos")
public class Contatos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private TipoContato tipoContato;
	
	@Column(nullable = false, length = 50)
	private String contatoTelefonico;
	
	public Contatos() {
		
	}
	
	public Contatos(Long id, TipoContato tipoContato, String contatoTelefonico) {
		this.id = id;
		this.tipoContato = tipoContato;
		this.contatoTelefonico = contatoTelefonico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContatoTelefonico() {
		return contatoTelefonico;
	}

	public void setContatoTelefonico(String contatoTelefonico) {
		this.contatoTelefonico = contatoTelefonico;
	}
	
	
	
	
	
	

}
