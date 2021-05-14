package com.contato.apicontato.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.modelmapper.ModelMapper;

import com.contato.apicontato.converter.ContatoConverter;
import com.contato.apicontato.models.Contato;
import com.contato.apicontato.repository.ContatoRepository;
import com.contato.apicontato.present.ContatoPresent;
import com.contato.apicontato.dto.ContatoDto;
import com.contato.apicontato.exception.ResourceNotFoundException;

@Service
public class ContatoService {
	@Autowired
	private ContatoRepository contatoRepository;
	@Autowired
	private ContatoConverter contatoConverter;
	
	public ResponseEntity<ContatoPresent> store(ContatoDto contatoDto) {
		ModelMapper modelMapper = new ModelMapper();
		Contato contato = modelMapper.map(contatoDto, Contato.class);
		return ResponseEntity.ok(contatoConverter.toModel(contatoRepository.save(contato)));
	}
	
	public ResponseEntity<Map<String, Boolean>> destroy(@PathVariable Long id) {
		Contato contato = contatoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contado com o id "+ id + " não existe"));
		
		contatoRepository.delete(contato);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<Contato> update(Long id, ContatoDto contatoDto) {
		Contato contato = contatoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contado com o id "+ id + " não existe"));
		contato.setEmail(contatoDto.getEmail());
		contato.setNome(contatoDto.getNome());
		contato.setSexo(contatoDto.getSexo());
		contato.setTelefone(contatoDto.getTelefone());
		
		Contato updateContato = contatoRepository.save(contato);
		
		return ResponseEntity.ok(updateContato);
	}
}
