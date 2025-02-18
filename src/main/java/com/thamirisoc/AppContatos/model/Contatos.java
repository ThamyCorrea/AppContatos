package com.thamirisoc.AppContatos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoContato tipoContato;
	
	@Column(nullable = false, length = 50)
	private String contato;
	
	@ManyToOne	
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	@JsonBackReference
    private Pessoas pessoa;
	
	public Contatos() {
		
	}	

	public Contatos(Long id, TipoContato tipoContato, String contato, Pessoas pessoa) {
		super();
		this.id = id;
		this.tipoContato = tipoContato;
		this.contato = contato;
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

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
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
				+ ", contato=" + contato
				+ ", pessoa=" + pessoa + "]";
	}
	
	
	
	

}
