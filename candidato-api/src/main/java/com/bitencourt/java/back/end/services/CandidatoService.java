package com.bitencourt.java.back.end.services;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.CandidatoRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.ExperienciaRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.ReferenciaRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.CandidatoResponseDTO;
import com.bitencourt.java.back.end.domain.entities.Candidato;
import com.bitencourt.java.back.end.domain.entities.Experiencia;
import com.bitencourt.java.back.end.domain.entities.Referencia;
import com.bitencourt.java.back.end.domain.enumerations.FlagsEnum;
import com.bitencourt.java.back.end.exceptions.CampoErrorSchemaException;
import com.bitencourt.java.back.end.exceptions.CandidatoDuplicadoException;
import com.bitencourt.java.back.end.exceptions.CandidatoNotFoundException;
import com.bitencourt.java.back.end.repositories.CandidatoRepository;
import com.bitencourt.java.back.end.utils.MergeUtil;

@Service
public class CandidatoService {
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	private static final String REMOVIDO_COM_SUCESSO_MENSAGEM = "{0} removido com sucesso";
	
	public CandidatoResponseDTO findById(long candidatoId) {
		Optional<Candidato> candidato = candidatoRepository.findById(candidatoId);
		
		if(candidato.isPresent()) {
			CandidatoResponseDTO candidatoResponse = CandidatoResponseDTO.convert(candidato.get());
			return candidatoResponse;
		}
		
		throw new CandidatoNotFoundException();
	}
	
	public CandidatoResponseDTO save(CandidatoRequestDTO candidatoRequest, String email, String senha) {
		validarDuplicidade(email);
		Candidato candidato = Candidato.convert(candidatoRequest);
		candidato.setEmail(email);
		candidato.setSenha(senha);
		Candidato candidatoSalvo = candidatoRepository.save(candidato);
		return CandidatoResponseDTO.convert(candidatoSalvo);
	}
	
	private void validarDuplicidade(String email) throws CandidatoDuplicadoException{
		Candidato candidato = candidatoRepository.findByEmail(email);
		
		if (Objects.nonNull(candidato)) {
			throw new CandidatoDuplicadoException();
		}
	}

	public MessageSucessoDTO delete(long candidatoId) {
		Optional<Candidato> candidato = candidatoRepository.findById(candidatoId);
		if (candidato.isPresent()) {
			candidatoRepository.delete(candidato.get());
			return new MessageSucessoDTO(MessageFormat.format(REMOVIDO_COM_SUCESSO_MENSAGEM, "Candidato"));
		}
		throw new CandidatoNotFoundException();
	}

	public CandidatoResponseDTO update(CandidatoRequestDTO candidatoRequest, long id) {
		Optional<Candidato> candidato = candidatoRepository.findById(id);
		if (candidato.isPresent()) {
			
			if (Objects.nonNull(candidatoRequest.getExperiencias()) && candidatoRequest.getExperiencias().size() > 0) {				
				validarAlteracaoExperiencia(candidatoRequest, candidato.get());
			}
			
			if (Objects.nonNull(candidatoRequest.getReferencias()) && candidatoRequest.getReferencias().size() > 0) {
				validarAlteracaoReferencias(candidatoRequest, candidato.get());
			}
			
			Candidato candidatoMerge = MergeUtil.diffCandidato(candidatoRequest, candidato.get());
			Candidato candidatoAtualizado = candidatoRepository.save(candidatoMerge);
			return CandidatoResponseDTO.convert(candidatoAtualizado);
		}
		
		throw new CandidatoNotFoundException();
	}

	private void validarAlteracaoReferencias(CandidatoRequestDTO candidatoRequest, Candidato candidato) {
		Set<Referencia> referenciasTratado = candidato.getReferencias();
		for(ReferenciaRequestDTO referencia : candidatoRequest.getReferencias()) {
			if ((referencia.getFlag().equals(FlagsEnum.DELETE) || referencia.getFlag().equals(FlagsEnum.UPDATE)) 
					&& Objects.nonNull(referencia.getReferenciaId())) {
				
				if (referencia.getFlag().equals(FlagsEnum.DELETE)) {
					referenciasTratado.removeIf(ref -> ref.getReferenciaId().equals(referencia.getReferenciaId()));
				} else {
					editarReferencia(referencia, referenciasTratado);
				}
				
			} else if (referencia.getFlag().equals(FlagsEnum.SAVE)) {
				referenciasTratado.add(Referencia.convert(referencia));
			} else {
				throw new CampoErrorSchemaException("referencia", "id");
			}
		}
		
		candidato.setReferencias(referenciasTratado);
		candidatoRequest.setReferencias(null);
	}

	private void editarReferencia(ReferenciaRequestDTO referenciaRequest, Set<Referencia> referenciasTratado) {
		for(Referencia referencia : referenciasTratado) {
			if (referencia.getReferenciaId().equals(referenciaRequest.getReferenciaId())) {
				referencia.setNome(referenciaRequest.getNome());
				referencia.setEmail(referenciaRequest.getEmail());
			}
		}
		
	}

	private void validarAlteracaoExperiencia(CandidatoRequestDTO candidatoRequest, Candidato candidato) {
		Set<Experiencia> experienciasTratado = candidato.getExperiencias();
		for(ExperienciaRequestDTO experiencia : candidatoRequest.getExperiencias()) {
			if ((experiencia.getFlag().equals(FlagsEnum.DELETE) || experiencia.getFlag().equals(FlagsEnum.UPDATE)) 
					&& Objects.nonNull(experiencia.getExperienciaId())) {
				if (experiencia.getFlag().equals(FlagsEnum.DELETE)) {
					experienciasTratado.removeIf(exp-> exp.getExperienciaId().equals(experiencia.getExperienciaId()));
				} else {
					editarExperiencia(experiencia, experienciasTratado);
				}
			} else if (experiencia.getFlag().equals(FlagsEnum.SAVE)) {
				experienciasTratado.add(Experiencia.convert(experiencia));
			} else {
				throw new CampoErrorSchemaException("experiencia", "id");
			}
		}
		
		candidato.setExperiencias(experienciasTratado);
		candidatoRequest.setExperiencias(null);
	}

	private void editarExperiencia(ExperienciaRequestDTO experienciaRequest, Set<Experiencia> experienciasTratado) {
		for(Experiencia experiencia : experienciasTratado) {
			if (experiencia.getExperienciaId().equals(experienciaRequest.getExperienciaId())) {
				experiencia.setCargo(experienciaRequest.getCargo());
				experiencia.setDataFim(experienciaRequest.getDataFim());
				experiencia.setDataInicio(experienciaRequest.getDataInicio());
				experiencia.setDescricao(experienciaRequest.getDescricao());
				experiencia.setNomeEmpresa(experienciaRequest.getNomeEmpresa());
			}
		}
		
	}

	public CandidatoResponseDTO updateCurriculo(long id, String url) {
		
		Optional<Candidato> candidato = candidatoRepository.findById(id);
		if (candidato.isPresent()) {
			candidato.get().setCurriculoUrl(url);
			Candidato candidatoAtualizado = candidatoRepository.save(candidato.get());
			return CandidatoResponseDTO.convert(candidatoAtualizado);
		} else {
			throw new CandidatoNotFoundException();
		}
		
	}

	public List<CandidatoResponseDTO> findAll() {
		List<Candidato> candidatos = candidatoRepository.findAll();
		if(Objects.nonNull(candidatos) && candidatos.size() > 0) {
			return candidatos.stream().map(CandidatoResponseDTO::convert).collect(Collectors.toList());		}
		throw new CandidatoNotFoundException();
	}
	
	public CandidatoResponseDTO login(String email, String senha) {
		Candidato candidato = candidatoRepository.findByEmail(email);
		if (Objects.nonNull(candidato)
				&& candidato.getEmail().equals(email)
				&& candidato.getSenha().equals(senha)) {
			return CandidatoResponseDTO.convert(candidato);			
		}
		throw new CandidatoNotFoundException();
	}

}