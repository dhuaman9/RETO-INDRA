package com.tipocambio.service;

import com.tipocambio.repository.TipoCambioRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tipocambio.dto.TipoCambioDTO;
import com.tipocambio.model.TipoCambio;

@Service
public class TipoCambioService {

	private final TipoCambioRepository tipoCambioRepository;

	public TipoCambioService(TipoCambioRepository tipoCambioRepository) {
		this.tipoCambioRepository = tipoCambioRepository;
	}

	/**
	 * Calcula el monto convertido según el tipo de cambio almacenado.
	 */
	public TipoCambioDTO calcularTipoCambio(TipoCambioDTO dto) {
		Optional<TipoCambio> tipocmbOpt = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(dto.getMonedaOrigen(),
				dto.getMonedaDestino());

		if (tipocmbOpt.isEmpty()) {
			throw new IllegalArgumentException(
					"Tipo de cambio no encontrado para " + dto.getMonedaOrigen() + " a " + dto.getMonedaDestino());
		}

		TipoCambio tipocmb = tipocmbOpt.get();

		// Conversión con BigDecimal
		BigDecimal monto = dto.getMonto();
		BigDecimal tipoCambio = tipocmb.getTipoCambio();
		BigDecimal montoConvertido = monto.multiply(tipoCambio).setScale(2, RoundingMode.HALF_UP);

		// Asignar valores al DTO
		dto.setTipoCambio(tipoCambio);
		dto.setMontoConvertido(montoConvertido);

		return dto;
	}

	//listamos los tipos de cambio
	public List<TipoCambioDTO> listarTiposCambio() {
		List<TipoCambio> tiposCambio = tipoCambioRepository.findAll();
		return tiposCambio.stream().map(this::convertirEntidadADTO).collect(Collectors.toList());
	}

	@Transactional
	public TipoCambioDTO guardarTipoCambio(TipoCambioDTO dto) {
		// Verificar si ya existe un tipo de cambio con la misma monedaOrigen y monedaDestino
		boolean existe = tipoCambioRepository
				.findByMonedaOrigenAndMonedaDestino(dto.getMonedaOrigen(), dto.getMonedaDestino()).isPresent();

		if (existe) {
			throw new IllegalArgumentException(
					"Ya existe un tipo de cambio para " + dto.getMonedaOrigen() + " a " + dto.getMonedaDestino());
		}

		// Crear una nueva entidad desde el DTO
		TipoCambio tipoCambio = convertirDTOaEntidad(dto);

		tipoCambio = tipoCambioRepository.save(tipoCambio);

		// Convertimos la entidad guardada a DTO y la retornamos
		return convertirEntidadADTO(tipoCambio);
	}

	@Transactional
	public TipoCambioDTO actualizarTipoCambio(Long id, TipoCambioDTO dto) {
		TipoCambio tipoCambio = tipoCambioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No se encontró el tipo de cambio con ID: " + id));

		
		tipoCambio.setMonedaOrigen(dto.getMonedaOrigen());
		tipoCambio.setMonedaDestino(dto.getMonedaDestino());
		tipoCambio.setTipoCambio(dto.getTipoCambio());

		
		tipoCambio = tipoCambioRepository.save(tipoCambio);

		return convertirEntidadADTO(tipoCambio);
	}

	private TipoCambio convertirDTOaEntidad(TipoCambioDTO dto) {
		return new TipoCambio(dto.getId(), dto.getMonedaOrigen(), dto.getMonedaDestino(), dto.getTipoCambio());
	}

	private TipoCambioDTO convertirEntidadADTO(TipoCambio tipoCambio) {
		return new TipoCambioDTO(tipoCambio.getId(), tipoCambio.getMonedaOrigen(), tipoCambio.getMonedaDestino(),
				tipoCambio.getTipoCambio());
	}

}
