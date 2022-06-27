package com.bitencourt.java.back.end.domain.dtos;

import java.util.Date;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageSucessoDTO {
	
	@ApiModelProperty(value = "Statos de retorno.", example = "200")
	HttpStatus status;
	@ApiModelProperty(value = "Mensagem de retorno.", example = "Candidato removido com sucesso.")
	String mensagem;
	@ApiModelProperty(example = "2010-02-20T00:00:00.000+00:00")
	Date timestamp;

	public MessageSucessoDTO(String mensagem) {
		this.status = HttpStatus.OK;
		this.mensagem = mensagem;
		this.timestamp = new Date();
	}
}
