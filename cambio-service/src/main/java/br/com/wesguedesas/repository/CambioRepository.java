package br.com.wesguedesas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wesguedesas.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);
}