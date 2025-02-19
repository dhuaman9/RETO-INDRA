package com.tipocambio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipocambio.model.TipoCambio;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long>{

	  Optional<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
	
}
