package com.thamirisoc.AppContatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thamirisoc.AppContatos.model.Contatos;

@Repository
public interface ContatosRepository extends JpaRepository<Contatos, Long>{
	
}
