package com.bitencourt.java.back.end.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.bitencourt.java.back.end.domain.dtos.requests.ReferenciaRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
@Table(name = "referencia", schema = "candidatos")
public class Referencia {
	
	@Column(name = "referencia_id")
	private String referenciaId;
	private String nome;
	private String email;
	
	public static Referencia convert(ReferenciaRequestDTO referenciaRequest) {
		Referencia referencia = new Referencia();
		
		String randomId = RandomStringUtils.randomAlphabetic(20);
		
		referencia.setReferenciaId(Objects.nonNull(referencia.getReferenciaId())? referencia.getReferenciaId() : randomId);		
		referencia.setEmail(referenciaRequest.getEmail());
		referencia.setNome(referenciaRequest.getNome());
		
		return null;
	}
}
