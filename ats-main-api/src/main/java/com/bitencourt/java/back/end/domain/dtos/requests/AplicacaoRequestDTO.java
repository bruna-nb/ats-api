package com.bitencourt.java.back.end.domain.dtos.requests;

import com.bitencourt.java.back.end.domain.enumerations.AplicacaoStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "VagaRequest")
@Data
public class AplicacaoRequestDTO {

	@ApiModelProperty(value = "Status da aplicação deste candidato para esta vaga.", required = true)
	AplicacaoStatusEnum status;

}