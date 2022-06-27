package com.bitencourt.java.back.end.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.CandidatoRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.CandidatoResponseDTO;
import com.bitencourt.java.back.end.services.CandidatoService;

import io.swagger.annotations.ApiParam;

@RestController
public class CandidatoController {
	
	@Autowired
	CandidatoService candidatoService;
	
	@GetMapping("/")
	public String getMensagem() {
		return "Spring boot is working!";
	}
	
	@GetMapping("/candidato")
	List<CandidatoResponseDTO> findAll() { 
		return candidatoService.findAll();
	}
	
	@GetMapping("/candidato/{id}")
	CandidatoResponseDTO findById(@PathVariable long id) { 
		return candidatoService.findById(id);
	}
	
	@PostMapping("/candidato")
	CandidatoResponseDTO newCandidato(
			@ApiParam(value = "email de login do candidato", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "email") String email,
			@ApiParam(value = "senha do candidato em encode Base64", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "base64-password") String senha,
			@RequestBody CandidatoRequestDTO candidatoDTO) {
		return candidatoService.save(candidatoDTO, email, senha);
	}
	
	@PatchMapping("/candidato/{id}")
	CandidatoResponseDTO updateCandidato(@RequestBody CandidatoRequestDTO candidatoDTO, @PathVariable long id) {
		return candidatoService.update(candidatoDTO, id);
	}
	
	@DeleteMapping("/candidato/{id}")
	MessageSucessoDTO deleteCandidato(@PathVariable long id) {
		return candidatoService.delete(id);
	}
	
	@PostMapping(path = "/curriculo/{id}")
	public CandidatoResponseDTO uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long id) throws IOException {
		String constr = "DefaultEndpointsProtocol=https;"
				+ "AccountName=curriculosatstotvs;"
				+ "AccountKey=rfsMENAxzAxNRs4bDMcqs+/6+deJDsja2gfOpGlc1JbHjUKvXrcsNrs9HEn9+JrtnSG7ex18EwDO+ASt3VrDoQ==;"
				+ "EndpointSuffix=core.windows.net";
		
		BlobContainerClient container = new BlobContainerClientBuilder().connectionString(constr).containerName("curriculos").buildClient();
		
		BlobClient blob = container.getBlobClient(file.getOriginalFilename());
		
		blob.upload(file.getInputStream(), file.getSize(), true);
		
		String url = blob.getBlobUrl();
		
		return candidatoService.updateCurriculo(id, url);
				
	}
	
	@DeleteMapping("/curriculo/{id}")
	CandidatoResponseDTO deleteCurriculo(@PathVariable long id) {
		return candidatoService.updateCurriculo(id, null);
		
	}

}