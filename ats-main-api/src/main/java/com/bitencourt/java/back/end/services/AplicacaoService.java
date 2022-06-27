package com.bitencourt.java.back.end.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.AplicacaoRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.AplicacaoResponseDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.CandidatoResponseDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.VagaResponseDTO;
import com.bitencourt.java.back.end.domain.entities.Aplicacao;
import com.bitencourt.java.back.end.exceptions.AplicacaoNotFoundException;
import com.bitencourt.java.back.end.exceptions.NotFoundException;
import com.bitencourt.java.back.end.repositories.AplicacaoRepository;

@Service
public class AplicacaoService {
	
	@Autowired
	private AplicacaoRepository aplicacaoRepository;
	
	@Autowired
	private CandidatoService candidatoService;
	
	@Autowired
	private VagaService vagaService;
	
	private static final String REMOVIDO_COM_SUCESSO_MENSAGEM = "{0} removida com sucesso";
	
	public AplicacaoResponseDTO findById(long aplicacaoId) {
		Optional<Aplicacao> aplicacao = aplicacaoRepository.findById(aplicacaoId);
		
		if(aplicacao.isPresent()) {
			CandidatoResponseDTO candidato = candidatoService.getCandidatoById(aplicacao.get().getIdCandidato());
			VagaResponseDTO vaga = vagaService.getVagaById(aplicacao.get().getIdVaga());
			AplicacaoResponseDTO aplicacaoResponse = AplicacaoResponseDTO.convert(aplicacao.get(), vaga, candidato);
			return aplicacaoResponse;
		}
		
		throw new AplicacaoNotFoundException();
	}
	
	public AplicacaoResponseDTO save(AplicacaoRequestDTO aplicacaoRequest, long idVaga, long idCandidato ) {
		VagaResponseDTO vaga = vagaService.getVagaById(idVaga);
		if (vaga == null) {
			throw new NotFoundException("Vaga");
		}
		
		CandidatoResponseDTO candidato = candidatoService.getCandidatoById(idCandidato); 
		if (candidato == null) {
			throw new NotFoundException("Candidato");
		}
		
		Aplicacao aplicacao = Aplicacao.convert(aplicacaoRequest, vaga.getId(), candidato.getId());
		Aplicacao aplicacaoSalva = aplicacaoRepository.save(aplicacao);
		AplicacaoResponseDTO aplicacaoResponse = AplicacaoResponseDTO.convert(aplicacaoSalva, vaga, candidato);
		
		return aplicacaoResponse;
	}
	

	public MessageSucessoDTO delete(long aplicacaoId) {
		Optional<Aplicacao> aplicacao = aplicacaoRepository.findById(aplicacaoId);
		if (aplicacao.isPresent()) {
			aplicacaoRepository.delete(aplicacao.get());
			return new MessageSucessoDTO(MessageFormat.format(REMOVIDO_COM_SUCESSO_MENSAGEM, "Aplicacao"));
		}
		throw new AplicacaoNotFoundException();
	}

	public AplicacaoResponseDTO update(AplicacaoRequestDTO aplicacaoRequest, long id) {
		Optional<Aplicacao> aplicacao = aplicacaoRepository.findById(id);
		if (aplicacao.isPresent()) {
			aplicacao.get().setStatus(Objects.nonNull(aplicacaoRequest.getStatus()) ? aplicacaoRequest.getStatus() : aplicacao.get().getStatus());
			Aplicacao aplicacaoAtualizada = aplicacaoRepository.save(aplicacao.get());
			VagaResponseDTO vaga = vagaService.getVagaById(aplicacao.get().getIdVaga());
			CandidatoResponseDTO candidato = candidatoService.getCandidatoById(aplicacao.get().getIdCandidato()); 
			return AplicacaoResponseDTO.convert(aplicacaoAtualizada, vaga, candidato);
		}
		
		throw new AplicacaoNotFoundException();
	}

	

	public List<AplicacaoResponseDTO> findAllByCandidato(long idCandidato) {
		try { 			
			List<Aplicacao> aplicacoes = aplicacaoRepository.findAllByIdCandidato(idCandidato);
			if(Objects.nonNull(aplicacoes) && aplicacoes.size() > 0) {
				List<AplicacaoResponseDTO> aplicacoesConvertidas = new ArrayList<AplicacaoResponseDTO>();
				preencherAplicacao(aplicacoes, aplicacoesConvertidas);
				return aplicacoesConvertidas;
			}
			throw new AplicacaoNotFoundException();
		} catch (Exception e) {			
			throw new AplicacaoNotFoundException();
		}
	}
	
	public List<AplicacaoResponseDTO> findAllByVaga(long idVaga) {
		List<Aplicacao> aplicacoes = aplicacaoRepository.findAllByIdVaga(idVaga);
		if(Objects.nonNull(aplicacoes) && aplicacoes.size() > 0) {
			List<AplicacaoResponseDTO> aplicacoesConvertidas = new ArrayList<AplicacaoResponseDTO>();
			preencherAplicacao(aplicacoes, aplicacoesConvertidas);
			return aplicacoesConvertidas;
		}
		throw new AplicacaoNotFoundException();
	}

	private void preencherAplicacao(List<Aplicacao> aplicacoes, List<AplicacaoResponseDTO> aplicacoesConvertidas) {
		for (Aplicacao aplicacao : aplicacoes) {
			VagaResponseDTO vaga = vagaService.getVagaById(aplicacao.getIdVaga());
			CandidatoResponseDTO candidato = candidatoService.getCandidatoById(aplicacao.getIdCandidato()); 
			aplicacoesConvertidas.add(AplicacaoResponseDTO.convert(aplicacao, vaga, candidato));
		}
				
	}
	

}