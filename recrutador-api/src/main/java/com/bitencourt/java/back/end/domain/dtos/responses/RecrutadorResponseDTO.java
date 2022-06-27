package com.bitencourt.java.back.end.domain.dtos.responses;

import javax.validation.constraints.Size;

import com.bitencourt.java.back.end.domain.entities.Recrutador;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@ApiModel("RecrutadorResponse")
public class RecrutadorResponseDTO {

	@ApiModelProperty(value = "id do candidato.", required = true, example = "127")
	private long id;
	
	@ApiModelProperty(value = "Nome completo do candidato.", required = true, example = "João da Silva")
	@Size(min = 4, max = 90)
	private String nome;
	
	@ApiModelProperty(value = "E-mail de login do candidato.", required = true, example = "jao.dasilva@exemplo.com")
	private String email;
	
	public static RecrutadorResponseDTO convert(Recrutador recrutador) {
		
		RecrutadorResponseDTO recrutadorResponse = new RecrutadorResponseDTO();
		recrutadorResponse.setId(recrutador.getId());
		recrutadorResponse.setNome(recrutador.getNome());
		recrutadorResponse.setEmail(recrutador.getEmail());

		return recrutadorResponse;
	}
}
