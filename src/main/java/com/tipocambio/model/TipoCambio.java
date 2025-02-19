package com.tipocambio.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "TIPO_CAMBIO")
public class TipoCambio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal  tipoCambio;
	
    
    public TipoCambio(Long id, String monedaOrigen, String monedaDestino, BigDecimal tipoCambio) {
    	this.id = id;
    	this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tipoCambio = tipoCambio;
    }
	
}
