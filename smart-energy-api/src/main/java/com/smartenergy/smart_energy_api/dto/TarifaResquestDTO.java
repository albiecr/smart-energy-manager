package com.smartenergy.smart_energy_api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Data Transfer Object (DTO) para requisição de verificação de tarifa.
<p> 
Contém a data e hora para a qual se deseja verificar se está no horário de tarifa ponta.
</p>
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarifaResquestDTO {
    
    // O usuário vai enviar um JSON assim: { "dataHora": "2025-10-20T19:00:00" }
    private LocalDateTime dataHora;

}
