package com.bitencourt.java.back.end.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
	
	String atributoNotFound;
	
	public NotFoundException(String atributoNotFound) {
		this.atributoNotFound = atributoNotFound;
	}

}
