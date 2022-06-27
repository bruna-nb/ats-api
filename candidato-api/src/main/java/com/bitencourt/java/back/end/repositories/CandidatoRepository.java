package com.bitencourt.java.back.end.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitencourt.java.back.end.domain.entities.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long>{
	
	Candidato findByEmail(String email);
	

}
