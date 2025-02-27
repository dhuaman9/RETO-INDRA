package com.tipocambio.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalcularTipoCambioRequestDTO {
	
	
	@NotNull(message = "El monto es obligatorio")
    private BigDecimal monto;

    @NotBlank(message = "La moneda origen es obligatoria")
    private String monedaOrigen;

    @NotBlank(message = "La moneda destino es obligatoria")
    private String monedaDestino;

}
