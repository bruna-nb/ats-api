package com.bitencourt.java.back.end.exceptions.advice;

import java.text.MessageFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bitencourt.java.back.end.domain.dtos.ErrorDTO;
import com.bitencourt.java.back.end.exceptions.AplicacaoNotFoundException;
import com.bitencourt.java.back.end.exceptions.CampoErrorSchemaException;
import com.bitencourt.java.back.end.exceptions.NotFoundException;

@ControllerAdvice(basePackages = "com.bitencourt.java.back.end.controllers")
public class VagaControllerAdvice {
	private static final String REQ_INVALIDA_DETAIL = "A requisição que busca criar ou alterar um(a) {0} não respeita o Schema. Campo {1} inválido.";
	private static final String NOT_FOUND_DETAIL = "Não foram encontrados registros de {0} para o id informado.";
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AplicacaoNotFoundException.class)
	public ErrorDTO handleAplicacaoNotFound(AplicacaoNotFoundException vagaNotFound) {
		ErrorDTO errorDTO= new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Aplicações não encontrada(s).");
		errorDTO.setTimestamp(new Date());
		return errorDTO;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorDTO handleNotFound(NotFoundException notFound) {
		ErrorDTO errorDTO= new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage(MessageFormat.format(NOT_FOUND_DETAIL, notFound.getAtributoNotFound()));
		errorDTO.setTimestamp(new Date());
		return errorDTO;
	}
	

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CampoErrorSchemaException.class)
	public ErrorDTO handleCampoErrorSchema(CampoErrorSchemaException errorSchema) {
		ErrorDTO errorDTO= new ErrorDTO();
		errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDTO.setMessage(MessageFormat.format(REQ_INVALIDA_DETAIL, errorSchema.getAtributo(), errorSchema.getCampo()));
		errorDTO.setTimestamp(new Date());
		return errorDTO;
	}
}
