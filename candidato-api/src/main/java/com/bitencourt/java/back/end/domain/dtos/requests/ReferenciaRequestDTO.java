package com.bitencourt.java.back.end.domain.dtos.requests;

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
@ApiModel(value = "ReferenciaRequest")
public class ReferenciaRequestDTO {
	
	@NotNull
	@ApiModelProperty(value = "flag que indica o que se deseja fazer com essa referência específica.", required = true)
	FlagsEnum flag;
	
	@ApiModelProperty(value = "Identificador da referência. Torna-se obrigatório caso  a 'flag' seja DELETE ou UPDATE.", required = false)
	String referenciaId;

	@NotNull
	@ApiModelProperty(value = "Nome da pessoa de contato para referência do candidato.", required = true, example = "Joana dos Santos")
	private String nome;
	
	@NotNull
	@ApiModelProperty(value = "E-mail da pessoa de contato.", required = true, example = "joana.santos@example.com")
	private String email;
}
