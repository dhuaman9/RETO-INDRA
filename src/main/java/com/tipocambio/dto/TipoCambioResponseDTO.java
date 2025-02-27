package com.tipocambio.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Evita enviar atributos nulos
public class TipoCambioResponseDTO {

		private Long  id;
	 	private BigDecimal monto;
	    private String monedaOrigen;
	    private String monedaDestino;
	    private BigDecimal tipoCambio;
	    private BigDecimal montoConvertido;
	
	
	
}
