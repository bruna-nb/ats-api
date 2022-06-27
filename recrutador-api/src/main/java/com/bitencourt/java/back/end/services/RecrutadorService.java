package com.bitencourt.java.back.end.services;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitencourt.java.back.end.domain.dtos.MessageSucessoDTO;
import com.bitencourt.java.back.end.domain.dtos.requests.RecrutadorRequestDTO;
import com.bitencourt.java.back.end.domain.dtos.responses.RecrutadorResponseDTO;
import com.bitencourt.java.back.end.domain.entities.Recrutador;
import com.bitencourt.java.back.end.exceptions.RecrutadorDuplicadoException;
import com.bitencourt.java.back.end.exceptions.RecrutadorNotFoundException;
import com.bitencourt.java.back.end.repositories.RecrutadorRepository;

@Service
public class RecrutadorService {
	
	@Autowired
	private RecrutadorRepository recrutadorRepository;
	
	private static final String REMOVIDO_COM_SUCESSO_MENSAGEM = "{0} removido com sucesso";
	
	public RecrutadorResponseDTO findById(long recrutadorId) {
		Optional<Recrutador> recrutador = recrutadorRepository.findById(recrutadorId);
		
		if(recrutador.isPresent()) {
			RecrutadorResponseDTO recrutadorResponse = RecrutadorResponseDTO.convert(recrutador.get());
			return recrutadorResponse;
		}
		
		throw new RecrutadorNotFoundException();
	}
	
	public RecrutadorResponseDTO save(RecrutadorRequestDTO recrutadorRequest, String email, String senha) {
		validarDuplicidade(email);
		Recrutador recrutador = Recrutador.convert(recrutadorRequest);
		recrutador.setEmail(email);
		recrutador.setSenha(senha);
		Recrutador recrutadorSalvo = recrutadorRepository.save(recrutador);
		return RecrutadorResponseDTO.convert(recrutadorSalvo);
	}
	
	private void validarDuplicidade(String email) throws RecrutadorDuplicadoException{
		Recrutador recrutador = recrutadorRepository.findByEmail(email);
		
		if (Objects.nonNull(recrutador)) {
			throw new RecrutadorDuplicadoException();
		}
	}

	public MessageSucessoDTO delete(long recrutadorId) {
		Optional<Recrutador> recrutador = recrutadorRepository.findById(recrutadorId);
		if (recrutador.isPresent()) {
			recrutadorRepository.delete(recrutador.get());
			return new MessageSucessoDTO(MessageFormat.format(REMOVIDO_COM_SUCESSO_MENSAGEM, "Recrutador"));
		}
		throw new RecrutadorNotFoundException();
	}

	public RecrutadorResponseDTO update(RecrutadorRequestDTO recrutadorRequest, long id) {
		Optional<Recrutador> recrutador = recrutadorRepository.findById(id);
		if (recrutador.isPresent()) {
			recrutador.get().setNome(Objects.nonNull(recrutadorRequest.getNome()) ? recrutadorRequest.getNome() : recrutador.get().getNome());
			Recrutador recrutadorAtualizado = recrutadorRepository.save(recrutador.get());
			return RecrutadorResponseDTO.convert(recrutadorAtualizado);
		}
		
		throw new RecrutadorNotFoundException();
	}

	
	public List<RecrutadorResponseDTO> findAll() {
		List<Recrutador> recrutadores = recrutadorRepository.findAll();
		if(Objects.nonNull(recrutadores) && recrutadores.size() > 0) {
			return recrutadores.stream().map(RecrutadorResponseDTO::convert).collect(Collectors.toList());
			}
		throw new RecrutadorNotFoundException();
	}

	public RecrutadorResponseDTO login(String email, String senha) {
		Recrutador recrutador = recrutadorRepository.findByEmail(email);
		if (Objects.nonNull(recrutador)
				&& recrutador.getEmail().equals(email)
				&& recrutador.getSenha().equals(senha)) {
			return RecrutadorResponseDTO.convert(recrutador);			
		}
		throw new RecrutadorNotFoundException();
	}

}