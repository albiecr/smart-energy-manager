package com.smartenergy.smart_energy_api.mapper;

import java.util.List;

import com.smartenergy.smart_energy_api.dto.HotelReadingDTO;
import com.smartenergy.smart_energy_api.model.HotelReading;

/**
 * Interface responsável por converter dados entre a Entidade (Banco) e o DTO (API).
 * O componentModel = "spring" permite injetar isso com @Autowired.
 */
@org.mapstruct.Mapper(componentModel = "spring")
public interface HotelReadingMapper {
    /**
     * Converte uma Entidade (Banco) para DTO (Json).
     * Como os nomes dos campos são IDÊNTICOS (ex: demandKw), 
     * não precisamos de anotações @Mapping extras. O MapStruct faz sozinho.
     */

    HotelReadingDTO toDTO(HotelReading entity);

    List<HotelReadingDTO> toDTOList(List<HotelReading> entities);
}
