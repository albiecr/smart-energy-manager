package com.smartenergy.smart_energy_api.dto;

/**
 * DTO para formatar a resposta do cálculo de custos.
 * Exemplo JSON: { "valorTotal": 87.5, "mensagem": "Cálculo realizado..." }
 */
public record CustoResponseDTO (Double valorTotal, String mensagem) {

}
