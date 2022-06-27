package com.bitencourt.java.back.end.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bitencourt.java.back.end.domain.dtos.responses.RecrutadorResponseDTO;
import com.bitencourt.java.back.end.exceptions.CampoErrorSchemaException;

@Service
public class RecrutadorService {
	
	public RecrutadorResponseDTO getRecrutadorById(long recrutadorId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/recrutador/" + recrutadorId;
		try {			
			ResponseEntity<RecrutadorResponseDTO> response = restTemplate.getForEntity(url, RecrutadorResponseDTO.class);
			return response.getBody();
		} catch (Exception e){
			throw new CampoErrorSchemaException("vaga", "idRecrutador");
		}
	}

}
