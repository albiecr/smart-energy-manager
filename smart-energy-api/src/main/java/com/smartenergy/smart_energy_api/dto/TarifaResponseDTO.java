package com.smartenergy.smart_energy_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) para resposta de verificação de tarifa.
 * <p>
 * Contém a informação se o horário é tarifa ponta e uma mensagem descritiva.
 * </p>
 */
@Data
@AllArgsConstructor
public class TarifaResponseDTO {
    // A resposta será: { "isHorarioPonta": true, "mensagem": "..." }
    private boolean horarioPonta;
    private String mensagem;
}
