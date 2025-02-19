package com.tipocambio.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipocambio.dto.TipoCambioDTO;
import com.tipocambio.service.TipoCambioService;

@RestController
@RequestMapping("/api/tipo-cambio")
public class TipoCambioController {

	private final TipoCambioService tipoCambioService;

	public TipoCambioController(TipoCambioService tipoCambioService) {
		this.tipoCambioService = tipoCambioService;
	}

	/**
	 * Endpoint para listar los tipos de cambio
	 */
	
	@GetMapping("/listar")
	public List<TipoCambioDTO> listarTiposCambio() {
		return tipoCambioService.listarTiposCambio();
	}

	/**
	 * Endpoint para calcular el tipo de cambio
	 */
	@PostMapping("/calcular")
	public ResponseEntity<TipoCambioDTO> calcularTipoCambio(@RequestBody TipoCambioDTO dto) {
		TipoCambioDTO resultado = tipoCambioService.calcularTipoCambio(dto);
		return ResponseEntity.ok(resultado);
	}

	/**
	 * Endpoint para guardar un nuevo tipo de cambio
	 */
	@PostMapping("/guardar")
	public ResponseEntity<TipoCambioDTO> guardarTipoCambio(@RequestBody TipoCambioDTO dto) {
		TipoCambioDTO resultado = tipoCambioService.guardarTipoCambio(dto);
		return ResponseEntity.ok(resultado);
	}

	/**
	 * Endpoint para actualizar un tipo de cambio existente por su ID
	 */
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<TipoCambioDTO> actualizarTipoCambio(@PathVariable Long id, @RequestBody TipoCambioDTO dto) {

		TipoCambioDTO resultado = tipoCambioService.actualizarTipoCambio(id, dto);
		return ResponseEntity.ok(resultado);
	}

}
