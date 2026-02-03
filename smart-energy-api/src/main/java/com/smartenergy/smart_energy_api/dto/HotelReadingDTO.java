package com.smartenergy.smart_energy_api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelReadingDTO {

    private Long id;
    private LocalDateTime timestamp;
    private Double demandKw;
    private Double temperatureC;
    private Double solarIrradiance;
    private Double humidity;

}
