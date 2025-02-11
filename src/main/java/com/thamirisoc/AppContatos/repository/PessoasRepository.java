package com.thamirisoc.AppContatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thamirisoc.AppContatos.model.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long>{
	

}
