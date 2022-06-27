package com.bitencourt.java.back.end.utils;

import java.util.Objects;

import com.bitencourt.java.back.end.domain.dtos.requests.VagaRequestDTO;
import com.bitencourt.java.back.end.domain.entities.Vaga;

public class MergeUtil {
	
	public static Vaga vagaDiff(VagaRequestDTO vagaRequest, Vaga vagaCadastrada) {
		Vaga vagaAtualizada = new Vaga();
		
		vagaAtualizada.setId(vagaCadastrada.getId());
		vagaAtualizada.setIdRecrutador(vagaCadastrada.getIdRecrutador());
		
		vagaAtualizada.setDescricao(Objects.nonNull(vagaRequest.getDescrição()) ? vagaRequest.getDescrição() : vagaCadastrada.getDescricao());
		vagaAtualizada.setRequisitos(Objects.nonNull(vagaRequest.getRequisitos()) ? vagaRequest.getRequisitos() : vagaCadastrada.getRequisitos());
		vagaAtualizada.setTitulo(Objects.nonNull(vagaRequest.getTitulo()) ? vagaRequest.getTitulo() : vagaCadastrada.getTitulo());
		
		return vagaAtualizada;
		
	}

}
