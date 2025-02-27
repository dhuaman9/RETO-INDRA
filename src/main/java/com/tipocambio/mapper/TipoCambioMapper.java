package com.tipocambio.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.tipocambio.dto.CalcularTipoCambioRequestDTO;
import com.tipocambio.dto.CalcularTipoCambioResponseDTO;
import com.tipocambio.dto.TipoCambioRequestDTO;
import com.tipocambio.dto.TipoCambioResponseDTO;
import com.tipocambio.model.TipoCambio;

//@Mapper(componentModel = "spring")
@Component
public class  TipoCambioMapper {
	

	
	 // Convierte de TipoCambioRequestDTO a Entidad TipoCambio
    public TipoCambio toEntity(TipoCambioRequestDTO dto) {
        return TipoCambio.builder()
                .monedaOrigen(dto.getMonedaOrigen())
                .monedaDestino(dto.getMonedaDestino())
                .tipoCambio(dto.getTipoCambio())
                .build();
    }

    // Convierte de Entidad a TipoCambioRequestDTO
    public TipoCambioRequestDTO toRequestDto(TipoCambio entity) {
        return TipoCambioRequestDTO.builder()
                .monedaOrigen(entity.getMonedaOrigen())
                .monedaDestino(entity.getMonedaDestino())
                .tipoCambio(entity.getTipoCambio())
                .build();
    }

    // Convierte de Entidad a TipoCambioResponseDTO
    public TipoCambioResponseDTO toResponseDto(TipoCambio entity) {
        return TipoCambioResponseDTO.builder()
        		.id(entity.getId())
                .monedaOrigen(entity.getMonedaOrigen())
                .monedaDestino(entity.getMonedaDestino())
                .tipoCambio(entity.getTipoCambio())
                .build();
    }

    // Convierte de CalcularTipoCambioRequestDTO  a CalcularTipoCambioResponseDTO
    public CalcularTipoCambioResponseDTO toResponseDTO(CalcularTipoCambioRequestDTO dto, TipoCambio tipoCambio, BigDecimal montoConTipoCambio) {
    	
       // BigDecimal montoConTipoCambio = dto.getMonto().multiply(tipoCambio.getTipoCambio());
        return CalcularTipoCambioResponseDTO.builder()
                .monto(dto.getMonto())
                .montoConTipoCambio(montoConTipoCambio)
                .monedaOrigen(dto.getMonedaOrigen())
                .monedaDestino(dto.getMonedaDestino())
                .tipoCambio(tipoCambio.getTipoCambio())
                .build();
    }

}
