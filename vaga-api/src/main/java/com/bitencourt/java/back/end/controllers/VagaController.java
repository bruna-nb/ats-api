package com.bitencourt.java.back.end.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitencourt.java.back.end.domain.dtos.ErrorDTO;
import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.VagaRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.VagaResponseDTO;
import com.bitencourt.java.back.end.services.VagaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VagaController {
	
	@Autowired
	VagaService vagaService;
	
	@ApiOperation(notes = "Endpoint para listar todas as vagas.", value = "Listar vagas", tags = {"Recurso para listar todas as vagas."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Lista consultada com sucesso.", response = VagaResponseDTO.class, responseContainer = "List"),
	               @ApiResponse(code = 404, message = "Nenhuma vaga encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@GetMapping("/vaga")
	List<VagaResponseDTO> findAll() { 
		return vagaService.findAll();
	}
	
	@ApiOperation(notes = "Endpoint para listar todas as vagas.", value = "Listar vagas", tags = {"Recurso para listar todas as vagas."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Lista consultada com sucesso.", response = VagaResponseDTO.class, responseContainer = "List"),
	               @ApiResponse(code = 404, message = "Nenhuma vaga encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@GetMapping("/vaga/vagaByRecrutador/{idRecrutador}")
	List<VagaResponseDTO> findAllByRecrutador(@PathVariable long idRecrutador) { 
		return vagaService.findAllByRecrutador(idRecrutador);
	}
	
	@ApiOperation(notes = "Endpoint para consultar uma vaga pelo ID.", value = "Consultar vaga", tags = {"Recurso para consultar uma vaga."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Vaga consultada com sucesso.", response = VagaResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhuma vaga encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })		
	@GetMapping("/vaga/{id}")
	VagaResponseDTO findById(@PathVariable long id) { 
		return vagaService.findById(id);
	}
	
	@ApiOperation(notes = "Endpoint para criar uma nova vaga.", value = "Cadastrar vaga", tags = {"Recurso para cadastrar uma vaga."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Vaga cadastrada com sucesso.", response = VagaResponseDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PostMapping("/vaga/{idRecrutador}")
	VagaResponseDTO newVaga(
			@RequestBody VagaRequestDTO vagaDTO, @PathVariable long idRecrutador) {
		return vagaService.save(vagaDTO, idRecrutador);
	}
	
	@ApiOperation(notes = "Endpoint para editar as informações de uma vaga.", value = "Editar vaga", tags = {"Recurso para editar uma vaga."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Recrutador editado com sucesso.", response = VagaResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhuma vaga encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PatchMapping("/vaga/{id}")
	VagaResponseDTO updateVaga(@RequestBody VagaRequestDTO vagaDTO, @PathVariable long id) {
		return vagaService.update(vagaDTO, id);
	}
	
	@ApiOperation(notes = "Endpoint para remover uma vaga.", value = "Deletar vaga", tags = {"Recurso para deletar uma vaga."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Vaga deletada com sucesso.", response = MessageSucessoDTO.class),
	               @ApiResponse(code = 404, message = "Nenhuma vaga encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })
	@DeleteMapping("/vaga/{id}")
	MessageSucessoDTO deleteVaga(@PathVariable long id) {
		return vagaService.delete(id);
	}

}