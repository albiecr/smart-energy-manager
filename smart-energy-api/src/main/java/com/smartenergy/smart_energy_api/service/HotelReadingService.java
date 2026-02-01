package com.smartenergy.smart_energy_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartenergy.smart_energy_api.dto.HotelReadingDTO;
import com.smartenergy.smart_energy_api.exception.InvalidDateRangeException;
import com.smartenergy.smart_energy_api.mapper.HotelReadingMapper;
import com.smartenergy.smart_energy_api.model.HotelReading;
import com.smartenergy.smart_energy_api.repository.HotelReadingRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service responsável pela lógica de negócios das leituras de energia.
 */
@Service
@RequiredArgsConstructor
public class HotelReadingService {

    private final HotelReadingRepository hotelReadingRepository;
    private final HotelReadingMapper hotelReadingMapper;

    /**
     * Busca as leituras de energia compreendidas em um intervalo de tempo específico.
     * <p>
     * Este método realiza uma validação prévia para garantir que a data de início
     * não seja posterior à data de fim.
     * </p>
     * * @param init Data e hora inicial do intervalo de busca (ex: 2025-01-01T10:00).
     * @param end    Data e hora final do intervalo de busca (ex: 2025-01-01T12:00).
     * @return Uma lista de {@link HotelReadingDTO} contendo os dados encontrados.
     * Retorna uma lista vazia se nenhuma leitura for encontrada.
     * @throws InvalidDateRangeException Se a data de 'init' for posterior à data de 'end'.
     */

    public List<HotelReadingDTO> hotelReadings(LocalDateTime init, LocalDateTime end) {
        
        // 1. Regra de Negócio: Validação de consistência temporal
        if(init.isAfter(end)) {
            throw new InvalidDateRangeException("Data de início não pode ser posterior à data de fim.");
        }

        // 2. Busca os dados brutos no banco (Entity)
        List<HotelReading> readings = hotelReadingRepository
                .findByTimestampBetweenOrderByTimestampAsc(init, end);

        // 3. Converte as Entities para DTOs (JSON amigável) usando o Mapper
        return hotelReadingMapper.toDTOList(readings);
    }
}
