package com.smartenergy.smart_energy_api.controller;

import com.smartenergy.smart_energy_api.dto.TarifaRequestDTO;
import com.smartenergy.smart_energy_api.dto.TarifaResponseDTO;
import com.smartenergy.smart_energy_api.service.TarifaCelescService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST responsável por expor os serviços relacionados às tarifas de energia.
 * <p>
 * Este controller gerencia as requisições HTTP que chegam na rota base "/api/tarifas",
 * permitindo que clientes externos consultem regras de negócio da Celesc (ex: Horário de Ponta).
 * </p>
 */
@RestController
@RequestMapping("api/tariffs")
public class TarifaCelescController {

    private final TarifaCelescService tarifaService;

    /**
     * Construtor para injeção de dependência do serviço de tarifas.
     *
     * @param tarifaService Instância do serviço gerenciada pelo Spring.
     */

    public TarifaCelescController(TarifaCelescService tarifaService) {
        this.tarifaService = tarifaService;
    }

    /**
     * Endpoint para verificar se uma data e hora específica corresponde ao Horário de Ponta.
     * <p>
     * Recebe um JSON contendo a data/hora e retorna um booleano junto com uma mensagem descritiva.
     * </p>
     *
     * @param request DTO contendo a data e hora a ser analisada (JSON no corpo da requisição).
     * @return {@link ResponseEntity} contendo o {@link TarifaResponseDTO} com o resultado da verificação.
     * <br>
     * Exemplo de chamada: <code>POST /api/tarifas/verificar</code>
     */

    @PostMapping("tarifas/verify")
    public ResponseEntity<TarifaResponseDTO> verificarHorario(@RequestBody TarifaRequestDTO request) {
        TarifaResponseDTO resposta = tarifaService.verificarHorario(request);
        return ResponseEntity.ok(resposta);
    }

}
