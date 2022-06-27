package com.bitencourt.java.back.end.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.bitencourt.java.back.end.domain.dtos.ErrorDTO;
import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.CandidatoRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.CandidatoResponseDTO;
import com.bitencourt.java.back.end.services.CandidatoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Candidato")
@RestController
public class CandidatoController {
	
	@Autowired
	CandidatoService candidatoService;
	
	@ApiOperation(notes = "Endpoint para listar todos os candidatos.", value = "Listar candidatos", tags = {"Recurso para listar todos os candidatos."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Lista consultada com sucesso.", response = CandidatoResponseDTO.class, responseContainer = "List"),
	               @ApiResponse(code = 404, message = "Nenhum candidato encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@GetMapping("/candidato")
	List<CandidatoResponseDTO> findAll() { 
		return candidatoService.findAll();
	}
	
	@ApiOperation(notes = "Endpoint para consultar um candidato pelo ID.", value = "Consultar candidato", tags = {"Recurso para consultar um candidato."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Candidato consultado com sucesso.", response = CandidatoResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum candidato encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })		
	@GetMapping("/candidato/{id}")
	CandidatoResponseDTO findById(@PathVariable long id) { 
		return candidatoService.findById(id);
	}
	
	@ApiOperation(notes = "Endpoint para criar um novo candidato.", value = "Cadastrar candidato", tags = {"Recurso para cadastrar um candidato."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Candidato cadastrado com sucesso.", response = CandidatoResponseDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PostMapping("/candidato")
	CandidatoResponseDTO newCandidato(
			@ApiParam(value = "email de login do candidato", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "email") String email,
			@ApiParam(value = "senha do candidato em encode Base64", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "base64-password") String senha,
			@RequestBody CandidatoRequestDTO candidatoDTO) {
		return candidatoService.save(candidatoDTO, email, senha);
	}
	
	@ApiOperation(notes = "Endpoint para editar as informações de um candidato.", value = "Editar candidato", tags = {"Recurso para editar um candidato."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Candidato editado com sucesso.", response = CandidatoResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum candidato encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PatchMapping("/candidato/{id}")
	CandidatoResponseDTO updateCandidato(@RequestBody CandidatoRequestDTO candidatoDTO, @PathVariable long id) {
		return candidatoService.update(candidatoDTO, id);
	}
	
	@ApiOperation(notes = "Endpoint para remover um candidato.", value = "Deletar candidato", tags = {"Recurso para deletar um candidato."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Candidato deletado com sucesso.", response = MessageSucessoDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum candidato encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })
	@DeleteMapping("/candidato/{id}")
	MessageSucessoDTO deleteCandidato(@PathVariable long id) {
		return candidatoService.delete(id);
	}
	
	@ApiOperation(notes = "Endpoint para gravar um currículo para um candidato. Para editar um currículo, basta inserir um novo.", value = "Inserir currículo candidato", tags = {"Recurso para inserir um currículo."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Currículo gravado com sucesso.", response = CandidatoResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum candidato encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })
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
	
	@ApiOperation(notes = "Endpoint para remover o currículo de um candidato.", value = "Remover currículo candidato", tags = {"Recurso para remover um currículo."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Currículo deletado com sucesso.", response = CandidatoResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum candidato encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })
	@DeleteMapping("/curriculo/{id}")
	CandidatoResponseDTO deleteCurriculo(@PathVariable long id) {
		return candidatoService.updateCurriculo(id, null);
		
	}
	
	@PutMapping("/login")
	MessageSucessoDTO loginCandidato(
			@ApiParam(value = "email de login do recrutador ou candidato", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "email") String email,
			@ApiParam(value = "senha do recrutador ou candidato em encode Base64", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "base64-password") String senha) {
		return candidatoService.login(email, senha);
	}

}