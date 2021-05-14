package com.contato.apicontato.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;

import com.contato.apicontato.models.Contato;
import com.contato.apicontato.present.ContatoPresent;

@Service
public class ContatoConverter {
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<ContatoPresent> toCollection(List<Contato> contatos) {
		return contatos.stream().map(contato -> toModel(contato)).collect(Collectors.toList());
	}
	
	public ContatoPresent toModel(Contato contato) {
		return modelMapper.map(contato, ContatoPresent.class);
	}
}
