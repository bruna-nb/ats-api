package com.bitencourt.java.back.end.domain.dtos.responses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bitencourt.java.back.end.domain.entities.Vaga;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class VagaResponseDTO {

	@ApiModelProperty(value = "id do candidato.", required = true, example = "127")
	private long id;
	
	@ApiModelProperty(value = "id do recrutador que criou a vaga", required = true, example = "18")
	private long idRecrutador;
	
	@NotNull
	@ApiModelProperty(value = "Título da vaga", required = true, example = "FullStack developer")
	@Size(min = 4, max = 90)
	private String titulo;
		
	@ApiModelProperty(value = "Descrição da vaga.", required = false, example = "Descrição da vaga")
	private String descrição;
	
	@ApiModelProperty(value = "Descrição dos requisitos da vaga.", required = false, example = "Conhecimento intermediário em Angular")
	private String requisitos;
	
	
	public static VagaResponseDTO convert(Vaga vaga) {
		
		VagaResponseDTO vagaResponse = new VagaResponseDTO();
		vagaResponse.setId(vaga.getId());
		vagaResponse.setIdRecrutador(vaga.getIdRecrutador());
		vagaResponse.setTitulo(vaga.getTitulo());
		vagaResponse.setDescrição(vaga.getDescricao());
		vagaResponse.setRequisitos(vaga.getRequisitos());

		return vagaResponse;
	}
}
