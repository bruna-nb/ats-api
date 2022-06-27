package com.bitencourt.java.back.end.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bitencourt.java.back.end.domain.dtos.requests.RecrutadorRequestDTO;

import lombok.Data;

@Data
@Entity(name="recrutador")
@Table(name="recrutador", schema = "recrutadores")
public class Recrutador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String email;
	private String senha;
	
	
	public static Recrutador convert(RecrutadorRequestDTO recrutadorRequest) {
		Recrutador recrutador = new Recrutador();		
		recrutador.setNome(recrutadorRequest.getNome());
		
		return recrutador;
	}
}
