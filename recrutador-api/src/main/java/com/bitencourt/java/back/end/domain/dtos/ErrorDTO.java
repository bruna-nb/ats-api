package com.bitencourt.java.back.end.domain.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDTO {
	
	private int status;
	private String message;
	private Date timestamp;

}
