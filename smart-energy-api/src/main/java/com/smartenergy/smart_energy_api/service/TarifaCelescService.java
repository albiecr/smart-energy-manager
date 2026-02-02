package com.smartenergy.smart_energy_api.service;

import com.smartenergy.smart_energy_api.dto.HotelReadingDTO;
import com.smartenergy.smart_energy_api.dto.TarifaRequestDTO;
import com.smartenergy.smart_energy_api.dto.TarifaResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Serviço responsável pelas regras de negócio das tarifas de energia da Celesc.
 * <p>
 * Esta classe centraliza a lógica de definição de horários de ponta (horário nobre)
 * e o cálculo financeiro do consumo baseado nessas tarifas (Verde).
 * </p>
 */
@Service
public class TarifaCelescService {

    /**
     * Preço do kWh em horário de ponta (R$).
     */
    private static final double PRECO_PONTA = 2.85;

    /**
     * Preço do kWh em horário fora de ponta (R$).
     */
    private static final double PRECO_FORA_PONTA = 0.65;

    /**
     * Início do horário de ponta (18:30).
     */
    private static final LocalTime INICIO_PONTA = LocalTime.of(18, 30);

    /**
     * Fim do horário de ponta (21:30).
     */
    private static final LocalTime FIM_PONTA = LocalTime.of(21, 30);

    // --- MÉTODOS PÚBLICOS (O que o Controller vai chamar) ---

    /**
     * Verifica se a data e hora informada na requisição corresponde ao Horário de Ponta.
     *
     * @param request DTO contendo a data e hora a ser verificada.
     * @return {@link TarifaResponseDTO} contendo o booleano de confirmação e uma mensagem descritiva.
     */

    public TarifaResponseDTO verificarHorario(TarifaRequestDTO request) {

        // 1. Pegamos a DATA do Request e convertemos para HORA
        LocalTime horaDaRequisicao = request.getDataHora().toLocalTime();

        // 2. Passamos essa hora para nossa regra de negócio
        boolean ehPonta = isHorarioPonta(horaDaRequisicao);

        String mensagem = ehPonta
                ? "Cuidado! Estamos no Horário de Ponta (Tarifa cara)."
                : "Horario Economico (Fora de Ponta).";
        return new TarifaResponseDTO(ehPonta, mensagem);
    }

    /**
     * Calcula o custo total estimado em Reais (R$) para uma lista de leituras de energia.
     * <p>
     * A metodologia de cálculo segue os seguintes passos para cada leitura:
     * <ol>
     * <li><b>Conversão para Energia:</b> A demanda (kW) é integrada num intervalo de 15 minutos (0.25h)
     * para obter o consumo em kWh (Fórmula: kW * 0.25).</li>
     * <li><b>Seleção de Tarifa:</b> Verifica o horário de cada leitura individualmente para aplicar
     * o preço de Ponta (horário nobre) ou Fora de Ponta.</li>
     * <li><b>Acumulação:</b> Soma o custo calculado de todas as leituras da lista.</li>
     * </ol>
     * </p>
     *
     * @param leituras Lista contendo os DTOs das leituras recuperadas do banco de dados.
     * Se a lista estiver vazia, retorna 0.0.
     * @return O valor financeiro total acumulado (R$) correspondente ao consumo da lista fornecida.
     */
    public Double calcularCustoTotal(List<HotelReadingDTO> leituras) {
        double custoTotal =0.0;

        for (HotelReadingDTO leitura: leituras) {
            // A. Calcula consumo: Potência (kW) * Tempo (0.25h ou 15min)
            double consumoKwh = leitura.getDemandKw() * 0.25;

            // B. Verifica qual tarifa aplicar para o horário DESTA leitura
            double tarifaAplicada = isHorarioPonta(leitura.getTimestamp().toLocalTime())
                    ? PRECO_PONTA
                    : PRECO_FORA_PONTA;


            // C. Acumula o custo
            custoTotal += (consumoKwh * tarifaAplicada);
        }

        return custoTotal;
    }

    // --- METODO PRIVADO (A regra interna) ---

    /**
     * Valida se um horário está dentro da faixa de ponta (18:30 às 21:30).
     */
    private boolean isHorarioPonta(LocalTime hora) {
        // Retorna TRUE se a hora NÃO for antes do inicio E for antes do fim.
        return !hora.isBefore(INICIO_PONTA) && hora.isBefore(FIM_PONTA);
    }
}
