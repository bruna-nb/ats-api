package com.bitencourt.java.back.end.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampoErrorSchemaException extends RuntimeException {
	String atributo;
	String campo;
}
