package com.bitencourt.java.back.end.domain.dtos.responses;

import com.bitencourt.java.back.end.domain.entities.Referencia;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Referencia")
@JsonInclude(Include.NON_NULL)
public class ReferenciaResponseDTO {
	
	@ApiModelProperty(value = "identificador da referência.", required = true, example = "26")
	private String referenciaId;
	
	@ApiModelProperty(value = "Nome da pessoa de contato para referência do candidato.", required = true, example = "Joana dos Santos")
	private String nome;
	
	@ApiModelProperty(value = "E-mail da pessoa de contato.", required = true, example = "joana.santos@example.com")
	private String email;
	
	public static ReferenciaResponseDTO convert(Referencia referencia) {
		ReferenciaResponseDTO referenciaResponseDTO = new ReferenciaResponseDTO();
		referenciaResponseDTO.setReferenciaId(referencia.getReferenciaId());
		referenciaResponseDTO.setNome(referencia.getNome());
		referenciaResponseDTO.setEmail(referencia.getEmail());	
		
		return referenciaResponseDTO;
	}
}
