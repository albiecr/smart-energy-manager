package com.smartenergy.smart_energy_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartenergy.smart_energy_api.model.HotelReading;

/**
 * Interface de acesso a dados (Repository) para a entidade {@link HotelReading}.
 * <p>
 * Esta interface estende {@link JpaRepository}, garantindo acesso imediato a operações
 * de CRUD (Create, Read, Update, Delete) sobre a tabela <b>hotel_readings</b>, 
 * abstraindo a complexidade de consultas SQL manuais.
 * </p>
 * * <h2>Funcionalidades Herdadas:</h2>
 * <ul>
 * <li><b>Persistência:</b> Métodos como <code>save()</code> e <code>saveAll()</code> para gravar leituras.</li>
 * <li><b>Busca:</b> Métodos como <code>findById()</code>, <code>findAll()</code> e <code>count()</code>.</li>
 * <li><b>Paginação:</b> Suporte nativo para buscar dados em páginas (PagingAndSortingRepository).</li>
 * </ul>
 * * A implementação concreta desta interface é gerada automaticamente pelo 
 * Spring Data JPA em tempo de execução (Dynamic Proxy).
 *
 * @author albiecr
 * @see HotelReading
 */
@Repository
public interface HotelReadingRepository extends JpaRepository<HotelReading, LocalDateTime> { 
    /**
     * Busca todas as leituras dentro de um intervalo de tempo específico.
     * <p>
     * O Spring cria automaticamente o SQL: 
     * <code>SELECT * FROM hotel_readings WHERE timestamp BETWEEN ? AND ?</code>
     * </p>
     * * @param start Data/Hora inicial
     * @param end Data/Hora final
     * @return Lista de leituras no período
     */

    List<HotelReading> findByTimestampBetweenOrderByTimestampAsc(LocalDateTime start, LocalDateTime end);

    List<HotelReading> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
