package com.bitencourt.java.back.end.domain.dtos;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ErrorDTO {
	
	@ApiModelProperty(value = "Status do erro", example = "404")
	private int status;
	@ApiModelProperty(value = "Mensagem de erro", example = "Candidato não encontrado.")
	private String message;
	@ApiModelProperty(example = "2010-02-20T00:00:00.000+00:00")
	private Date timestamp;

}
