package com.tipocambio.validation;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.tipocambio.dto.TipoCambioRequestDTO;

@Component
public class TipoCambioValidator {

	public void validar(TipoCambioRequestDTO dto) {
		if (dto.getMonedaOrigen().equalsIgnoreCase(dto.getMonedaDestino())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "La moneda origen y destino no pueden ser iguales.");
		}
		if (dto.getTipoCambio().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El tipo de cambio debe ser mayor a 0.");
		}
	}

}
