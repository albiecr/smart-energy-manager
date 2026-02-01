package com.smartenergy.smart_energy_api.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;



@Service
public class TarifaCelescService {

    public static final LocalTime tarifaPontaInicio = LocalTime.of(18, 0); // 18:00
    public static final LocalTime tarifaPontaFim = LocalTime.of(21, 0);   // 21:00

    public boolean isHorarioPonta(LocalDateTime dataHora){
        DayOfWeek diaSemana = dataHora.getDayOfWeek();
        LocalTime hora = dataHora.toLocalTime();

        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return false;
        }

        return hora.isAfter(tarifaPontaInicio) && hora.isBefore(tarifaPontaFim);
        }

}
