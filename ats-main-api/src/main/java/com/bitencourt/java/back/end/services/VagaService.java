package com.bitencourt.java.back.end.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bitencourt.java.back.end.domain.dtos.responses.VagaResponseDTO;
import com.bitencourt.java.back.end.exceptions.CampoErrorSchemaException;

@Service
public class VagaService {
	
	public VagaResponseDTO getVagaById(long vagaId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8082/vaga/" + vagaId;
		try {			
			ResponseEntity<VagaResponseDTO> response = restTemplate.getForEntity(url, VagaResponseDTO.class);
			return response.getBody();
		} catch (Exception e){
			throw new CampoErrorSchemaException("aplicacao", "idVaga");
		}
	}

}
