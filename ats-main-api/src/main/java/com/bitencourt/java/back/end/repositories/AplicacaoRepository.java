package com.bitencourt.java.back.end.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitencourt.java.back.end.domain.entities.Aplicacao;

@Repository
public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long>{
	
	List<Aplicacao> findAllByIdVaga(long idVaga);
	
	List<Aplicacao> findAllByIdCandidato(long idCandidato);

}
