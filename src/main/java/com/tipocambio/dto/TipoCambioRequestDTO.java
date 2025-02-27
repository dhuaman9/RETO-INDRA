package com.tipocambio.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoCambioRequestDTO {

	@NotBlank(message = "La moneda origen es obligatoria")
	private String monedaOrigen; // No permite null, "", ni " "

	@NotBlank(message = "La moneda destino es obligatoria")
	private String monedaDestino; // No permite null, "", ni " "

	@NotNull(message = "El tipo de cambio es obligatorio")
	private BigDecimal tipoCambio; // Solo valida null, pero no valores vacíos

}
