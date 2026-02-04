package com.smartenergy.smart_energy_api.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Data Transfer Object (DTO) para requisição de verificação de tarifa.
<p> 
Contém a data e hora para a qual se deseja verificar se está no horário de tarifa ponta.
</p>
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarifaRequestDTO {
    
    /**
     * A data e hora a ser verificada.
     * Exemplo JSON: "2025-10-20T19:00:00"
     */
    @NotNull(message = "O campo dataHora é obrigatório.")
    private LocalDateTime dataHora;

}
