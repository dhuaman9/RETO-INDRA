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

import com.tipocambio.dto.CalcularTipoCambioRequestDTO;
import com.tipocambio.dto.CalcularTipoCambioResponseDTO;
import com.tipocambio.dto.TipoCambioRequestDTO;
import com.tipocambio.dto.TipoCambioResponseDTO;
import com.tipocambio.service.TipoCambioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tipo-cambio")
@RequiredArgsConstructor
@Slf4j
public class TipoCambioController {

	private final TipoCambioService tipoCambioService;


    /**
     * Endpoint para listar todos los tipos de cambio
     */
	@Operation(summary = "Listar tipos de cambio", description = "Devuelve todos los tipos de cambio disponibles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de tipos de cambio obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay datos disponibles")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<TipoCambioResponseDTO>> listarTiposCambio() {
    	
    	log.info(" Listando tipos de cambio");
    	
        List<TipoCambioResponseDTO> tiposCambio = tipoCambioService.listarTiposDeCambio();

        if (tiposCambio.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si no hay datos
        }

        return ResponseEntity.ok(tiposCambio); // Devuelve 200 OK con la lista
    }

    /**
     *  para calcular el tipo de cambio
     */
	 @Operation(summary = "Calcular tipo de cambio", description = "Calcula el tipo de cambio basado en la moneda de origen y destino")
	    @ApiResponses({
	            @ApiResponse(responseCode = "200", description = "Tipo de cambio calculado correctamente"),
	            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
	    })
    @PostMapping("/calcular")
    public ResponseEntity<CalcularTipoCambioResponseDTO> calcularTipoCambio(
            @Valid @RequestBody CalcularTipoCambioRequestDTO requestDTO) {
    	
    	log.info(" inicio calculo del tipo de cambio");
        CalcularTipoCambioResponseDTO responseDTO = tipoCambioService.calcularTipoCambio(requestDTO);
        
        log.info("Fin del calculo del tipo de cambio ");
        
        return ResponseEntity.ok(responseDTO);
    }

    /**
     *  para guardar un nuevo tipo de cambio
     */
	 @Operation(summary = "Guardar tipo de cambio", description = "Guarda un nuevo tipo de cambio en la base de datos")
	    @ApiResponses({
	            @ApiResponse(responseCode = "200", description = "Tipo de cambio guardado correctamente"),
	            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
	    })
    @PostMapping("/guardar")
    public ResponseEntity<TipoCambioResponseDTO> crearTipoCambio(
            @Valid @RequestBody TipoCambioRequestDTO requestDTO) {
        TipoCambioResponseDTO responseDTO = tipoCambioService.guardarTipoCambio(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     *  para actualizar un tipo de cambio existente por su ID
     */
	 @Operation(summary = "Actualizar tipo de cambio", description = "Actualiza un tipo de cambio existente por su ID")
	    @ApiResponses({
	            @ApiResponse(responseCode = "200", description = "Tipo de cambio actualizado correctamente"),
	            @ApiResponse(responseCode = "404", description = "Tipo de cambio no encontrado")
	    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoCambioResponseDTO> actualizarTipoCambio(
            @PathVariable Long id, @Valid @RequestBody TipoCambioRequestDTO requestDTO) {
        TipoCambioResponseDTO responseDTO = tipoCambioService.actualizarTipoCambio(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    
    

}
