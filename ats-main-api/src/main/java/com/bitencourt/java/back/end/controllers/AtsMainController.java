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
import com.bitencourt.java.back.end.domain.dtos.requests.AplicacaoRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.AplicacaoResponseDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.VagaResponseDTO;
import com.bitencourt.java.back.end.services.AplicacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API principal do serviço de ATS. Aqui estão os endpoints de aplicação para uma vaga.")
@RestController
public class AtsMainController {
	
	@Autowired
	AplicacaoService aplicacaoService;
	
	@ApiOperation(notes = "Endpoint para listar todas as candidaturas de um candidato.", value = "Listar aplicações por candidato.", tags = {"Recurso para listar todas as aplicações de um candidato."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Lista consultada com sucesso.", response = VagaResponseDTO.class, responseContainer = "List"),
	               @ApiResponse(code = 404, message = "Nenhuma aplicação encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@GetMapping("/aplicacao/aplicacaoByCandidato/{idCandidato}")
	List<AplicacaoResponseDTO> findAllByCandidato(@PathVariable long idCandidato) { 
		return aplicacaoService.findAllByCandidato(idCandidato);
	}
	
	@ApiOperation(notes = "Endpoint para listar todas as candidaturas para uma vaga.", value = "Listar aplicações por vaga.", tags = {"Recurso para listar todas as aplicações para uma vaga."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Lista consultada com sucesso.", response = VagaResponseDTO.class, responseContainer = "List"),
	               @ApiResponse(code = 404, message = "Nenhuma aplicação encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@GetMapping("/aplicacao/aplicacaoByVaga/{idVaga}")
	List<AplicacaoResponseDTO> findAllByVaga(@PathVariable long idVaga) { 
		return aplicacaoService.findAllByCandidato(idVaga);
	}	

	
	@ApiOperation(notes = "Endpoint para criar uma nova aplicação/candidatura.", value = "Cadastrar aplicação", tags = {"Recurso para cadastrar uma aplicação."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Aplicação cadastrada com sucesso.", response = VagaResponseDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 404, message = "Vaga ou candidato não encontrados", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PostMapping("/aplicacao/{idVaga}/{idCandidato}")
	AplicacaoResponseDTO newAplicacao(
			@RequestBody AplicacaoRequestDTO aplicacaoDTO, @PathVariable long idVaga, @PathVariable long idCandidato) {
		return aplicacaoService.save(aplicacaoDTO, idVaga, idCandidato);
	}
	
	@ApiOperation(notes = "Endpoint para editar as informações de uma aplicação.", value = "Editar aplicação", tags = {"Recurso para editar uma aplicação."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Aplicação editada com sucesso.", response = VagaResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhuma aplicação encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PatchMapping("/aplicacao/{id}")
	AplicacaoResponseDTO updateAplicacao(@RequestBody AplicacaoRequestDTO aplicacaoDTO, @PathVariable long id) {
		return aplicacaoService.update(aplicacaoDTO, id);
	}
	
	@ApiOperation(notes = "Endpoint para remover uma aplicação.", value = "Deletar aplicação", tags = {"Recurso para deletar uma aplicação."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Aplicação deletada com sucesso.", response = MessageSucessoDTO.class),
	               @ApiResponse(code = 404, message = "Nenhuma aplicação encontrada.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })
	@DeleteMapping("/aplicacao/{id}")
	MessageSucessoDTO deleteVaga(@PathVariable long id) {
		return aplicacaoService.delete(id);
	}

}