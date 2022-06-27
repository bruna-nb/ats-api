package com.bitencourt.java.back.end.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitencourt.java.back.end.domain.entities.Recrutador;

@Repository
public interface RecrutadorRepository extends JpaRepository<Recrutador, Long>{
	
	Recrutador findByEmail(String email);
	

}
