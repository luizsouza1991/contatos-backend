package com.contato.apicontato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contato.apicontato.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	Contato findById(long id);
}