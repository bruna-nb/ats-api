package com.bitencourt.java.back.end.domain.dtos;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class MessageSucessoDTO {
	
	HttpStatus status;
	String mensagem;
	Date timestamp;

	public MessageSucessoDTO(String mensagem) {
		this.status = HttpStatus.OK;
		this.mensagem = mensagem;
		this.timestamp = new Date();
	}
}
