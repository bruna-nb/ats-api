package com.bitencourt.java.back.end.domain.dtos.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "VagaRequest")
@Data
public class VagaRequestDTO {

	@NotNull
	@ApiModelProperty(value = "Título da vaga", required = true, example = "FullStack developer")
	@Size(min = 4, max = 90)
	private String titulo;
		
	@ApiModelProperty(value = "Descrição da vaga.", required = false, example = "Descrição da vaga")
	private String descrição;
	
	@ApiModelProperty(value = "Descrição dos requisitos da vaga.", required = false, example = "Conhecimento intermediário em Angular")
	private String requisitos;

}