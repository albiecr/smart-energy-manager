package com.smartenergy.smart_energy_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) para resposta de verificação de tarifa.
 * <p>
 * Contém a informação se o horário é tarifa ponta e uma mensagem descritiva.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarifaResponseDTO {

    /**
     * Indica se é horário de ponta (True) ou não (False).
     * O @JsonProperty garante que o JSON será: "isHorarioPonta": true
     */
    @JsonProperty("isHorarioPonta")
    private boolean horarioPonta;
    
    private String mensagem;
}
