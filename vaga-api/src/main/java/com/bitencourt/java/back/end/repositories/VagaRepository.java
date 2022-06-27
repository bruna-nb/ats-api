package com.bitencourt.java.back.end.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitencourt.java.back.end.domain.entities.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long>{
	
	List<Vaga> findAllByIdRecrutador(long idRecrutador);

}
