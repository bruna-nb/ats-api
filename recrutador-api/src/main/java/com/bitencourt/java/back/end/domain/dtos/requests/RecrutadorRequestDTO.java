package com.bitencourt.java.back.end.domain.dtos.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Candidato")
@Data
public class RecrutadorRequestDTO {

	@NotNull
	@ApiModelProperty(value = "Nome completo do candidato.", required = true, example = "João da Silva")
	@Size(min = 4, max = 90)
	private String nome;
		
	@ApiModelProperty(value = "Telefone de contato do candidato.", required = false, example = "8321649647")
	private String telefone;

}