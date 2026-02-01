package com.smartenergy.smart_energy_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um registro unificado de consumo energético e condições climáticas.
 * <p>
 * Esta entidade mapeia a tabela <b>hotel_readings</b> no banco de dados, servindo
 * como a unidade fundamental de séries temporais do sistema. Cada instância
 * corresponde a uma leitura sincronizada de múltiplos sensores (medidores de energia
 * e estação meteorológica) em um momento específico.
 * </p>
 *
 * @author albiecr
 */
@Data                   // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor      // Gera o construtor vazio obrigatório para JPA
@AllArgsConstructor     // Gera o construtor com todos os campos
@Entity
@Table(name = "hotel_readings")
public class HotelReading {

    /**
     * O carimbo de data e hora exato da leitura.
     * <p>
     * Atua como a Chave Primária (PK) da entidade. As leituras geralmente
     * ocorrem em intervalos de 15 minutos.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o número sozinho (1, 2, 3...)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    /**
     * A Demanda de Potência Ativa Total do hotel.
     * <p>
     * Unidade: <b>Quilowatts (kW)</b>.
     * <br>
     * Este valor é calculado somando-se as leituras de todos os medidores 
     * setoriais (quartos, cozinha, lavanderia, etc.) naquele instante.
     * </p>
     */
    @Column(name = "demand_kw")
    private Double demandKw;

    /**
     * A temperatura ambiente registrada na região (Aveiro).
     * <p>
     * Unidade: <b>Graus Celsius (°C)</b>.
     * <br>
     * Utilizada para correlação entre consumo de energia (ar condicionado/aquecimento)
     * e variações climáticas.
     * </p>
     */
    @Column(name = "temperature_c")
    private Double temperatureC;

    /**
     * A irradiação solar global incidente.
     * <p>
     * Unidade: <b>Watts por metro quadrado (W/m²)</b>.
     * <br>
     * Indica a potência do sol no momento da leitura.
     * </p>
     */
    @Column(name = "solar_irradiance")
    private Double solarIrradiance;

    /**
     * A umidade relativa do ar média.
     * <p>
     * Unidade: <b>Porcentagem (%)</b>.
     * </p>
     */
    @Column(name = "humidity")
    private Double humidity;
}