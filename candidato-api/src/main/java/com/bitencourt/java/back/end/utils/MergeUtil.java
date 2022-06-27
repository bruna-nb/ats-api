package com.bitencourt.java.back.end.utils;

import java.util.Objects;

import com.bitencourt.java.back.end.domain.dtos.requests.CandidatoRequestDTO;
import com.bitencourt.java.back.end.domain.entities.Candidato;

public class MergeUtil {
	
	public static Candidato diffCandidato(CandidatoRequestDTO candidatoRequest, Candidato candidatoCadastrado) {
		Candidato candidato = new Candidato();
//		Informações não editáveis pelo PATCH
		candidato.setId(candidatoCadastrado.getId());
		candidato.setCurriculoUrl(candidatoCadastrado.getCurriculoUrl());
		candidato.setSenha(candidatoCadastrado.getSenha());
		candidato.setEmail(candidatoCadastrado.getEmail());
		
//		Informações editáveis pelo PATCH
		candidato.setNome(Objects.nonNull(candidatoRequest.getNome()) ? candidatoRequest.getNome() : candidatoCadastrado.getNome());
		candidato.setDescricao(Objects.nonNull(candidatoRequest.getDescricao()) ? candidatoRequest.getDescricao() : candidatoCadastrado.getDescricao());
		candidato.setTelefone(Objects.nonNull(candidatoRequest.getTelefone()) ? candidatoRequest.getTelefone() : candidatoCadastrado.getTelefone());
		
//		Informações que ja foram tratadas e editadas em outros métodos
		candidato.setExperiencias(candidatoCadastrado.getExperiencias());
		candidato.setReferencias(candidatoCadastrado.getReferencias());
		
		return candidato;
		
	}

}
