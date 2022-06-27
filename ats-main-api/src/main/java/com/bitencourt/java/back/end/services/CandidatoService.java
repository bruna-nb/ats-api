package com.bitencourt.java.back.end.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bitencourt.java.back.end.domain.dtos.responses.CandidatoResponseDTO;
import com.bitencourt.java.back.end.exceptions.CampoErrorSchemaException;

@Service
public class CandidatoService {
	
	public CandidatoResponseDTO getCandidatoById(long candidatoId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/candidato/" + candidatoId;
		try {			
			ResponseEntity<CandidatoResponseDTO> response = restTemplate.getForEntity(url, CandidatoResponseDTO.class);
			return response.getBody();
		} catch (Exception e){
			throw new CampoErrorSchemaException("aplicacao", "idCandidato");
		}
	}

}
