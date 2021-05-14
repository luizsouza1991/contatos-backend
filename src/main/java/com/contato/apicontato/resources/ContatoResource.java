package com.contato.apicontato.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contato.apicontato.models.Contato;
import com.contato.apicontato.repository.ContatoRepository;
import com.contato.apicontato.services.ContatoService;
import com.contato.apicontato.dto.ContatoDto;
import com.contato.apicontato.present.ContatoPresent;
import com.contato.apicontato.converter.ContatoConverter;
import com.contato.apicontato.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "*")
public class ContatoResource {
		@Autowired
		ContatoRepository contatoRepository;
		
		@Autowired
		ContatoService contatoService;
		
		@Autowired
		ContatoConverter contatoConverter;
		
		@GetMapping("/contatos")
		public List<ContatoPresent> index() {
			return contatoConverter.toCollection(contatoRepository.findAll());
		}
		
		@GetMapping("/contato/{id}")
		public ResponseEntity<Contato> getBookById(@PathVariable Long id) {
			Contato contato = contatoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Contado com o id "+ id + " não existe"));
			return ResponseEntity.ok(contato);
		}
		
		@PostMapping("/contato")
		public ResponseEntity<ContatoPresent> store(@RequestBody ContatoDto contatoDto) {
			return contatoService.store(contatoDto);
		}
		
		@DeleteMapping("/contato/{id}")
		public ResponseEntity<Map<String, Boolean>> destroy(@PathVariable Long id) {
			return contatoService.destroy(id);
		}
		
		@PutMapping("/contato/{id}")
		public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody ContatoDto contatoDto) {
			return contatoService.update(id, contatoDto);	
		}
}
