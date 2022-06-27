package com.bitencourt.java.back.end.domain.dtos.responses;

import com.bitencourt.java.back.end.domain.entities.Aplicacao;
import com.bitencourt.java.back.end.domain.enumerations.AplicacaoStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AplicacaoResponseDTO {
	
	@ApiModelProperty(value = "Status da aplicação.", required = true)
	AplicacaoStatusEnum status;
	
	@ApiModelProperty(value = "Candidato que aplicou para esta vaga.", required = true)
	CandidatoResponseDTO candidato;
	
	@ApiModelProperty(value = "Vaga para a qual o candidato está aplicando.", required = true)
	VagaResponseDTO vaga;

	public static AplicacaoResponseDTO convert(Aplicacao aplicacao, VagaResponseDTO vaga,
			CandidatoResponseDTO candidato) {
		AplicacaoResponseDTO aplicacaoResponse = new AplicacaoResponseDTO();
		aplicacaoResponse.setStatus(aplicacao.getStatus());
		aplicacaoResponse.setCandidato(candidato);
		aplicacaoResponse.setVaga(vaga);
		return aplicacaoResponse;
	}
	


}
