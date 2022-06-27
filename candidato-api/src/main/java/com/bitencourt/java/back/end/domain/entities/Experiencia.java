package com.bitencourt.java.back.end.domain.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.bitencourt.java.back.end.domain.dtos.requests.ExperienciaRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
@Table(name = "experiencia", schema = "candidatos")
public class Experiencia {
	
	@Column(name = "experiencia_id")
	private String experienciaId;
	private String cargo;
	private String nomeEmpresa;
	private Date dataInicio;
	private Date dataFim;
	private String descricao;
	
	public static Experiencia convert(ExperienciaRequestDTO experienciaRequest) {
		Experiencia experiencia = new Experiencia();
		String randomId = RandomStringUtils.randomAlphabetic(20);
		
		experiencia.setExperienciaId(Objects.nonNull(experienciaRequest.getExperienciaId())? experienciaRequest.getExperienciaId() : randomId);
		experiencia.setCargo(experienciaRequest.getCargo());
		experiencia.setNomeEmpresa(experienciaRequest.getNomeEmpresa());
		experiencia.setDataInicio(experienciaRequest.getDataInicio());
		experiencia.setDataFim(experienciaRequest.getDataFim());
		experiencia.setDescricao(experienciaRequest.getDescricao());
		
		return experiencia;
	}
}
