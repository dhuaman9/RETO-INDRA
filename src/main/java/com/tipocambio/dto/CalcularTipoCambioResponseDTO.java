package com.tipocambio.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CalcularTipoCambioResponseDTO {

	private BigDecimal monto;
	private BigDecimal montoConTipoCambio;
	private String monedaOrigen;
	private String monedaDestino;
	private BigDecimal tipoCambio;

}
