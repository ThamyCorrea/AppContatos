package com.thamirisoc.AppContatos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne	
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	@JsonBackReference
    private Pessoas pessoa;
	
	public Contatos() {
		
	}	

	public Contatos(Long id, TipoContato tipoContato, String contatoTelefonico, Pessoas pessoa) {
		super();
		this.id = id;
		this.tipoContato = tipoContato;
		this.contatoTelefonico = contatoTelefonico;
		this.pessoa = pessoa;
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

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Contatos [id=" + id 
				+ ", tipoContato=" + tipoContato 
				+ ", contatoTelefonico=" + contatoTelefonico
				+ ", pessoa=" + pessoa + "]";
	}
	
	
	
	

}
