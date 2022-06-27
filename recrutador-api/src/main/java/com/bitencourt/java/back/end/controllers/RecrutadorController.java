package com.bitencourt.java.back.end.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.bitencourt.java.back.end.domain.dtos.ErrorDTO;
import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.RecrutadorRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.RecrutadorResponseDTO;
import com.bitencourt.java.back.end.services.RecrutadorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RecrutadorController {
	
	@Autowired
	RecrutadorService recrutadorService;
	
	@ApiOperation(notes = "Endpoint para listar todos os recrutadores.", value = "Listar recrutadores", tags = {"Recurso para listar todos os recrutadores."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Lista consultada com sucesso.", response = RecrutadorResponseDTO.class, responseContainer = "List"),
	               @ApiResponse(code = 404, message = "Nenhum recrutador encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@GetMapping("/recrutador")
	List<RecrutadorResponseDTO> findAll() { 
		return recrutadorService.findAll();
	}
	
	@ApiOperation(notes = "Endpoint para consultar um recrutador pelo ID.", value = "Consultar recrutador", tags = {"Recurso para consultar um recrutador."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Recrutador consultado com sucesso.", response = RecrutadorResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum recrutador encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })		
	@GetMapping("/recrutador/{id}")
	RecrutadorResponseDTO findById(@PathVariable long id) { 
		return recrutadorService.findById(id);
	}
	
	@ApiOperation(notes = "Endpoint para criar um novo recrutador.", value = "Cadastrar recrutador", tags = {"Recurso para cadastrar um recrutador."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Candidato cadastrado com sucesso.", response = RecrutadorResponseDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PostMapping("/recrutador")
	RecrutadorResponseDTO newRecrutador(
			@ApiParam(value = "email de login do recrutador", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "email") String email,
			@ApiParam(value = "senha do recrutador em encode Base64", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "base64-password") String senha,
			@RequestBody RecrutadorRequestDTO recrutadorDTO) {
		return recrutadorService.save(recrutadorDTO, email, senha);
	}
	
	@ApiOperation(notes = "Endpoint para editar as informações de um recrutador.", value = "Editar recrutador", tags = {"Recurso para editar um recrutador."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Candidato editado com sucesso.", response = RecrutadorResponseDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum recrutador encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })	
	@PatchMapping("/recrutador/{id}")
	RecrutadorResponseDTO updateRecrutador(@RequestBody RecrutadorRequestDTO recrutadorDTO, @PathVariable long id) {
		return recrutadorService.update(recrutadorDTO, id);
	}
	
	@ApiOperation(notes = "Endpoint para remover um recrutador.", value = "Deletar recrutador", tags = {"Recurso para deletar um recrutador."})
	@ApiResponses({
	               @ApiResponse(code = 200, message = "Recrutador deletado com sucesso.", response = MessageSucessoDTO.class),
	               @ApiResponse(code = 404, message = "Nenhum recrutador encontrado.", response = ErrorDTO.class),
	               @ApiResponse(code = 400, message = "Requisição inválida.", response = ErrorDTO.class),
	               @ApiResponse(code = 500, message = "O serviço não está disponível no momento.", response = ErrorDTO.class)
	               })
	@DeleteMapping("/recrutador/{id}")
	MessageSucessoDTO deleteRecrutador(@PathVariable long id) {
		return recrutadorService.delete(id);
	}
	
	@PutMapping("/recrutador/login")
	MessageSucessoDTO loginRecrutador(
			@ApiParam(value = "email de login do recrutador", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "email") String email,
			@ApiParam(value = "senha do recrutador em encode Base64", required = true, allowEmptyValue = false, allowMultiple = false) @RequestHeader(value = "base64-password") String senha) {
		return recrutadorService.login(email, senha);
	}

}