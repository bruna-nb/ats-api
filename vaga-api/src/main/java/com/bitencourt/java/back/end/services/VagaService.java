package com.bitencourt.java.back.end.services;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.VagaRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.VagaResponseDTO;
import com.bitencourt.java.back.end.domain.entities.Vaga;
import com.bitencourt.java.back.end.exceptions.VagaNotFoundException;
import com.bitencourt.java.back.end.repositories.VagaRepository;
import com.bitencourt.java.back.end.utils.MergeUtil;

@Service
public class VagaService {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	private static final String REMOVIDO_COM_SUCESSO_MENSAGEM = "{0} removida com sucesso";
	
	public VagaResponseDTO findById(long vagaId) {
		Optional<Vaga> vaga = vagaRepository.findById(vagaId);
		
		if(vaga.isPresent()) {
			VagaResponseDTO vagaResponse = VagaResponseDTO.convert(vaga.get());
			return vagaResponse;
		}
		
		throw new VagaNotFoundException();
	}
	
	public VagaResponseDTO save(VagaRequestDTO vagaRequest, long idRecrutador) {
		Vaga vaga = Vaga.convert(vagaRequest);
		vaga.setIdRecrutador(idRecrutador);
		Vaga vagaSalva = vagaRepository.save(vaga);
		return VagaResponseDTO.convert(vagaSalva);
	}
	

	public MessageSucessoDTO delete(long vagaId) {
		Optional<Vaga> vaga = vagaRepository.findById(vagaId);
		if (vaga.isPresent()) {
			vagaRepository.delete(vaga.get());
			return new MessageSucessoDTO(MessageFormat.format(REMOVIDO_COM_SUCESSO_MENSAGEM, "Vaga"));
		}
		throw new VagaNotFoundException();
	}

	public VagaResponseDTO update(VagaRequestDTO vagaRequest, long id) {
		Optional<Vaga> vaga = vagaRepository.findById(id);
		if (vaga.isPresent()) {
			Vaga vagaMerge = MergeUtil.vagaDiff(vagaRequest, vaga.get());
			Vaga vagaAtualizada = vagaRepository.save(vagaMerge);
			return VagaResponseDTO.convert(vagaAtualizada);
		}
		
		throw new VagaNotFoundException();
	}

	
	public List<VagaResponseDTO> findAll() {
		List<Vaga> vagas = vagaRepository.findAll();
		if(Objects.nonNull(vagas) && vagas.size() > 0) {
			return vagas.stream().map(VagaResponseDTO::convert).collect(Collectors.toList());
			}
		throw new VagaNotFoundException();
	}

	public List<VagaResponseDTO> findAllByRecrutador(long idRecrutador) {
		List<Vaga> vagas = vagaRepository.findAllByIdRecrutador(idRecrutador);
		if(Objects.nonNull(vagas) && vagas.size() > 0) {
			return vagas.stream().map(VagaResponseDTO::convert).collect(Collectors.toList());
			}
		throw new VagaNotFoundException();
	}

}