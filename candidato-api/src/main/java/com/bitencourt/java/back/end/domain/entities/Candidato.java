package com.bitencourt.java.back.end.domain.entities;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bitencourt.java.back.end.domain.dtos.requests.CandidatoRequestDTO;

import lombok.Data;

@Data
@Entity(name="candidato")
@Table(name="candidato", schema = "candidatos")
public class Candidato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String descricao;
	private String curriculoUrl;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="experiencia", joinColumns = @JoinColumn(name = "candidato_id"),  foreignKey = @ForeignKey(name = "experiencia_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Experiencia> experiencias;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="referencia", joinColumns = @JoinColumn(name = "candidato_id"), foreignKey = @ForeignKey(name = "referencia_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Referencia> referencias;
	
	public static Candidato convert(CandidatoRequestDTO candidatoRequest) {
		Candidato candidato = new Candidato();
		
		candidato.setNome(candidatoRequest.getNome());
    		candidato.setTelefone(candidatoRequest.getTelefone());
		candidato.setDescricao(candidatoRequest.getDescricao());
		if (Objects.nonNull(candidatoRequest.getExperiencias())) {			
			candidato.setExperiencias(candidatoRequest.getExperiencias().stream().map(Experiencia::convert).collect(Collectors.toSet()));
		}
		
		if (Objects.nonNull(candidatoRequest.getReferencias())){
			candidato.setReferencias(candidatoRequest.getReferencias().stream().map(Referencia::convert).collect(Collectors.toSet()));
		}
		
		return candidato;
	}
}
