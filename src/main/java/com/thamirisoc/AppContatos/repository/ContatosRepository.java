package com.thamirisoc.AppContatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thamirisoc.AppContatos.model.Contatos;

public interface ContatosRepository extends JpaRepository<Contatos, Long>{
	
}
