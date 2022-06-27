package com.bitencourt.java.back.end.domain.dtos.responses;

import java.util.Date;

import com.bitencourt.java.back.end.domain.entities.Experiencia;
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
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "ExperienciaResponse")
public class ExperienciaResponseDTO {
	
	@ApiModelProperty(value = "identificador da experiência.", required = true, example = "54896")
	private String experienciaId;

	@ApiModelProperty(value = "Cargo ocupado pelo candidato na respectiva experiência.", required = true, example = "Analista de desenvolvimento.")
	private String cargo;
	
	@ApiModelProperty(value = "Nome da empresa onde ocorreu a respectiva experiência.", required = false, example = "TOTVS")
	private String nomeEmpresa;
	
	@ApiModelProperty(value = "Data de início da experiência.", required = true, example = "2010-02-20T00:00:00.000+00:00")
	private Date dataInicio;
	
	@ApiModelProperty(value = "Data de término da experiência. Será retornado nulo caso este seja o cargo atual do candidato.", required = false, example = "20/09/2020")
	private Date dataFim;
	
	@ApiModelProperty(value = "Descrição da experiência.", required = false, example = "Desenvolvimento de atividades relacionadas a desenvolvimento de software.")
	private String descricao;
	
	public static ExperienciaResponseDTO convert(Experiencia experiencia) {
		
		ExperienciaResponseDTO experienciaResponse = new ExperienciaResponseDTO();
		experienciaResponse.setExperienciaId(experiencia.getExperienciaId());
		experienciaResponse.setCargo(experiencia.getCargo());
		experienciaResponse.setNomeEmpresa(experiencia.getNomeEmpresa());
		experienciaResponse.setDataInicio(experiencia.getDataInicio());
		experienciaResponse.setDataFim(experiencia.getDataFim());
		experienciaResponse.setDescricao(experiencia.getDescricao());
				
		return experienciaResponse;
	}
}
