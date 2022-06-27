package com.bitencourt.java.back.end.domain.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class MessageSucessoDTO {
	
	int status;
	String mensagem;
	Date timestamp;

	public MessageSucessoDTO(String mensagem) {
		this.status = 200;
		this.mensagem = mensagem;
		this.timestamp = new Date();
	}
}
