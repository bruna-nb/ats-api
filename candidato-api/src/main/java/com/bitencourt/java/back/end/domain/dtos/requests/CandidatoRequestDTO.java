package com.bitencourt.java.back.end.domain.dtos.requests;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "CandidatoRequest")
@Data
public class CandidatoRequestDTO {

	@NotNull
	@ApiModelProperty(value = "Nome completo do candidato.", required = true, example = "João da Silva")
	@Size(min = 4, max = 90)
	private String nome;
		
	@ApiModelProperty(value = "Telefone de contato do candidato.", required = false, example = "8321649647")
	private String telefone;
	
	@ApiModelProperty(value = "Descrição do perfil do candidato.", required = false, example = "Olá, sou o João, tenho 27 anos e 6 anos de experiência como desenvolvedor na linguagem C#. (...)")
	@Size(min = 0, max = 400)
	private String descricao;
	
	@ApiModelProperty(value = "Lista de experiências cadastradas para o candidato.", required = false)
	List<ExperienciaRequestDTO> experiencias;
	
	@ApiModelProperty(value = "Lista de referências cadastradas para o candidato.", required = false)
	List<ReferenciaRequestDTO> referencias;

}