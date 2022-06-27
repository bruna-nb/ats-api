package com.bitencourt.java.back.end.domain.dtos.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.bitencourt.java.back.end.domain.enumerations.FlagsEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "Experiencia")
public class ExperienciaRequestDTO {
	@NotNull
	@ApiModelProperty(value = "flag que indica o que se deseja fazer com essa experiência específica.", required = true)
	FlagsEnum flag;
	
	@ApiModelProperty(value = "Identificador da experiencia. Torna-se obrigatório caso  a 'flag' seja DELETE ou UPDATE.", required = false)
	String experienciaId;
	
	@NotNull
	@ApiModelProperty(value = "Cargo ocupado pelo candidato na respectiva experiência.", required = true, example = "Analista de desenvolvimento.")
	private String cargo;
	
	@ApiModelProperty(value = "Nome da empresa onde ocorreu a respectiva experiência.", required = false, example = "TOTVS")
	private String nomeEmpresa;
	
	@NotNull
	@ApiModelProperty(value = "Data de início da experiência.", required = true, example = "16/05/2010")
	private Date dataInicio;
	
	@ApiModelProperty(value = "Data de término da experiência. Deve ser enviado nulo caso este seja o cargo atual do candidato.", required = false, example = "20/09/2020")
	private Date dataFim;
	
	@ApiModelProperty(value = "Descrição da experiência.", required = false, example = "Desenvolvimento de atividades relacionadas a desenvolvimento de software.")
	private String descricao;
	
}
