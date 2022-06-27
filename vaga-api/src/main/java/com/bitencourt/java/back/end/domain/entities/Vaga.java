package com.bitencourt.java.back.end.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bitencourt.java.back.end.domain.dtos.requests.VagaRequestDTO;

import lombok.Data;

@Data
@Entity(name="vaga")
@Table(name="vaga", schema = "vagas")
public class Vaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idRecrutador;
	private String titulo;
	private String descricao;
	private String requisitos;
	
	
	public static Vaga convert(VagaRequestDTO vagaRequest) {
		Vaga vaga = new Vaga();
		vaga.setTitulo(vagaRequest.getTitulo());
		vaga.setDescricao(vagaRequest.getDescrição());
		vaga.setRequisitos(vagaRequest.getRequisitos());	
		
		return vaga;
	}
}
