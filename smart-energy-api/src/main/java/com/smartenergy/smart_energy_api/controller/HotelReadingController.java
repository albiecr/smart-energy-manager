package com.smartenergy.smart_energy_api.controller;

import com.smartenergy.smart_energy_api.dto.HotelReadingDTO;
import com.smartenergy.smart_energy_api.service.HotelReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST responsável por expor os endpoints relacionados às leituras de energia do hotel.
 * <p>
 * Este controlador gerencia as requisições HTTP para consulta de dados de consumo.
 * </p>
 */
@RestController
@RequestMapping("/api/readings")
@RequiredArgsConstructor
public class HotelReadingController {

    private final HotelReadingService service;

    /**
     * Recupera uma lista de leituras de energia compreendidas num intervalo de tempo específico.
     * <p>
     * Exemplo de chamada:
     * {@code GET /api/leituras?inicio=2025-01-01T10:00:00&fim=2025-01-01T12:00:00}
     * </p>
     *
     * @param init Data e hora inicial do filtro (formato ISO 8601).
     * @param end    Data e hora final do filtro (formato ISO 8601).
     * @return ResponseEntity contendo a lista de {@link HotelReadingDTO} encontrados.
     */

    @GetMapping
    public ResponseEntity<List<HotelReadingDTO>> getReadings (
            @RequestParam("init") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime init,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        var readings = service.hotelReadings(init,end);

        return ResponseEntity.ok(readings);
    }
}
