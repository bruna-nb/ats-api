package com.bitencourt.java.back.end.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bitencourt.java.back.end.domain.dtos.requests.AplicacaoRequestDTO;
import com.bitencourt.java.back.end.domain.enumerations.AplicacaoStatusEnum;

import lombok.Data;

@Data
@Entity(name="aplicacao")
@Table(name="aplicacao", schema = "aplicacoes")
public class Aplicacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idVaga;
	private long idCandidato;
	private AplicacaoStatusEnum status;
	

	
	
	public static Aplicacao convert(AplicacaoRequestDTO aplicacaoRequest, long idVaga, long idCandidato) {
		Aplicacao aplicacao = new Aplicacao();
		aplicacao.setStatus(aplicacaoRequest.getStatus());
		aplicacao.setIdVaga(idVaga);
		aplicacao.setIdCandidato(idCandidato);
		return aplicacao;
	}
}
