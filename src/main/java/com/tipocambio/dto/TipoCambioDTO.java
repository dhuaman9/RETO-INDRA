package com.tipocambio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  // No incluir valores nulos en la respuesta JSON
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioDTO {
	
	private Long id;
	private BigDecimal monto;
	private String monedaOrigen;
	private String monedaDestino;
	private BigDecimal tipoCambio;
	private BigDecimal montoConvertido;

	
    public TipoCambioDTO(Long id, String monedaOrigen, String monedaDestino, BigDecimal tipoCambio) {
    	this.id = id;
    	this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tipoCambio = tipoCambio;
    }
	
}
