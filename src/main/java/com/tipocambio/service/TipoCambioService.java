package com.tipocambio.service;

import com.tipocambio.repository.TipoCambioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tipocambio.dto.CalcularTipoCambioRequestDTO;
import com.tipocambio.dto.CalcularTipoCambioResponseDTO;
import com.tipocambio.dto.TipoCambioRequestDTO;
import com.tipocambio.dto.TipoCambioResponseDTO;
import com.tipocambio.mapper.TipoCambioMapper;
import com.tipocambio.model.TipoCambio;
import com.tipocambio.validation.TipoCambioValidator;

@Service
@RequiredArgsConstructor
@Slf4j
public class TipoCambioService {

	private final TipoCambioRepository tipoCambioRepository;
	private final TipoCambioMapper tipoCambioMapper;
	private final TipoCambioValidator tipoCambioValidator;

//	public TipoCambioService(TipoCambioRepository tipoCambioRepository) {  si se usa el @RequiredArgsConstructor, no tiene razon de ser este constructor 
//		this.tipoCambioRepository = tipoCambioRepository;
//	}

	/**
	 * Calcula el monto convertido según el tipo de cambio almacenado.
	 */
	public CalcularTipoCambioResponseDTO calcularTipoCambio(CalcularTipoCambioRequestDTO dto) {
		log.info("Se inicia el calculo de tipo de cambio para {} -> {}", dto.getMonedaOrigen(), dto.getMonedaDestino());

		
		TipoCambio tipoCambio = tipoCambioRepository
				.findByMonedaOrigenAndMonedaDestino(dto.getMonedaOrigen(), dto.getMonedaDestino())
				.orElseThrow(() -> {
					log.error("Tipo de cambio no encontrado para {} -> {}", dto.getMonedaOrigen(), dto.getMonedaDestino());
					return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de cambio no encontrado");
				});

		
		// Aquí se realiza el cálculo
	    BigDecimal montoConTipoCambio = dto.getMonto().multiply(tipoCambio.getTipoCambio());

	    // Pasamos solo los objetos necesarios al mapper
	    log.info("Tipo de cambio encontrado: {}", tipoCambio);
	    return tipoCambioMapper.toResponseDTO(dto, tipoCambio, montoConTipoCambio);
	}

	
	/**
	 *  lista los tipos de cambio
	 */
	
	public List<TipoCambioResponseDTO> listarTiposDeCambio() {
		List<TipoCambio> lista = tipoCambioRepository.findAll();
		return lista.stream().map(tipoCambioMapper::toResponseDto).collect(Collectors.toList());
	}

	/**
	 * Guarda un nuevo tipo de cambio.
	 */
	@Transactional
	public TipoCambioResponseDTO guardarTipoCambio(TipoCambioRequestDTO dto) {
		log.info("Guardando tipo de cambio para {} -> {}", dto.getMonedaOrigen(), dto.getMonedaDestino());
		tipoCambioValidator.validar(dto); //  valida antes de guardar
		
		
		boolean existeTipoCambio = tipoCambioRepository.existsByMonedaOrigenAndMonedaDestino(
	            dto.getMonedaOrigen(), dto.getMonedaDestino());
		
		if (existeTipoCambio) {
			log.warn("Ya existe un tipo de cambio para {} -> {}", dto.getMonedaOrigen(), dto.getMonedaDestino());
	        throw new ResponseStatusException(HttpStatus.CONFLICT, 
	            "Ya existe un tipo de cambio para " + dto.getMonedaOrigen() + " -> " + dto.getMonedaDestino());
	    }
		
		TipoCambio tipoCambio = tipoCambioRepository.save(tipoCambioMapper.toEntity(dto));
		
		log.info("Tipo de cambio guardado  con ID: {}", tipoCambio.getId());
		
		return tipoCambioMapper.toResponseDto(tipoCambio);
	}
	
	
	/**
	 * Actualiza un tipo de cambio.
	 */
	@Transactional
	public TipoCambioResponseDTO actualizarTipoCambio(Long id, TipoCambioRequestDTO dto) {
		
		log.info("Actualizando tipo de cambio con ID: {}", id);
		
		TipoCambio tipoCambio = tipoCambioRepository.findById(id)
				.orElseThrow(() -> {
					log.error("Tipo de cambio con ID {} no encontrado", id);
					return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de cambio no encontrado");
				});

		tipoCambioValidator.validar(dto); // Validación antes de actualizar

		tipoCambio.setMonedaOrigen(dto.getMonedaOrigen());
		tipoCambio.setMonedaDestino(dto.getMonedaDestino());
		tipoCambio.setTipoCambio(dto.getTipoCambio());

		TipoCambio actualizado = tipoCambioRepository.save(tipoCambio);
		
		log.info("Tipo de cambio actualizado correctamente con ID: {}", actualizado.getId());

		
		return tipoCambioMapper.toResponseDto(actualizado);
	}



}
