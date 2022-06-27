package com.bitencourt.java.back.end.domain.dtos.responses;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import com.bitencourt.java.back.end.domain.entities.Candidato;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CandidatoResponseDTO {

	@ApiModelProperty(value = "id do candidato.", required = true, example = "127")
	private long id;
	
	@ApiModelProperty(value = "Nome completo do candidato.", required = true, example = "João da Silva")
	@Size(min = 4, max = 90)
	private String nome;
	
	@ApiModelProperty(value = "E-mail de login do candidato.", required = true, example = "jao.dasilva@exemplo.com")
	private String email;
	
	@ApiModelProperty(value = "Telefone de contato do candidato.", required = false, example = "8321649647")
	private String telefone;
	
	@ApiModelProperty(value = "Descrição do perfil do candidato.", required = false, example = "Olá, sou o João, tenho 27 anos e 6 anos de experiência como desenvolvedor na linguagem C#. (...)")
	@Size(min = 0, max = 400)
	private String descricao;
	
	@ApiModelProperty(value = "URL pública onde pode ser acessado o currículo do candidato.", required = false)
	private String curriculoUrl;
	
	@ApiModelProperty(value = "Lista de experiências cadastaradas para o candidato.", required = false)
	List<ExperienciaResponseDTO> experiencias;
	
	@ApiModelProperty(value = "Lista de referências cadastradas para o candidato.", required = false)
	List<ReferenciaResponseDTO> referencias;
	
	public static CandidatoResponseDTO convert(Candidato candidato) {
		
		CandidatoResponseDTO candidatoResponse = new CandidatoResponseDTO();
		
		candidatoResponse.setId(candidato.getId());
		candidatoResponse.setNome(candidato.getNome());
		candidatoResponse.setEmail(candidato.getEmail());
		candidatoResponse.setTelefone(candidato.getTelefone());
		candidatoResponse.setDescricao(candidato.getDescricao());
		candidatoResponse.setCurriculoUrl(candidato.getCurriculoUrl());
		if (Objects.nonNull(candidato.getExperiencias())) {			
			candidatoResponse.setExperiencias(candidato.getExperiencias().stream().map(ExperienciaResponseDTO::convert).collect(Collectors.toList()));
		}
		if (Objects.nonNull(candidato.getReferencias())) {			
			candidatoResponse.setReferencias(candidato.getReferencias().stream().map(ReferenciaResponseDTO::convert).collect(Collectors.toList()));		
		}
		
		return candidatoResponse;
	}
}
