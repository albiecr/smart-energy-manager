package com.smartenergy.smart_energy_api.service;

import com.smartenergy.smart_energy_api.dto.CustoResponseDTO;
import com.smartenergy.smart_energy_api.dto.HotelReadingDTO;
import com.smartenergy.smart_energy_api.dto.TarifaRequestDTO;
import com.smartenergy.smart_energy_api.dto.TarifaResponseDTO;
import com.smartenergy.smart_energy_api.model.HotelReading;
import com.smartenergy.smart_energy_api.repository.HotelReadingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
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
     * Repositório de acesso às leituras do hotel.
     */

    private final HotelReadingRepository repository;

    /**
     * Construtor para injeção de dependência do repositório.
     *
     * @param repository Instância do repositório gerenciada pelo Spring.
     */
    public TarifaCelescService(HotelReadingRepository repository) {
        this.repository = repository;
    }

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

    /**
     * Calcula o custo total de energia para um determinado mês e ano, baseado nas leituras armazenadas no banco de dados.
     * <p>
     * Este metodo recupera todas as leituras do período especificado, converte-as para DTOs
     * e reutiliza a lógica de cálculo existente para determinar o custo total.
     * </p>
     *
     * @param ano O ano para o qual o custo deve ser calculado (ex: 2024).
     * @param mes O mês para o qual o custo deve ser calculado (1-12).
     * @return {@link CustoResponseDTO} contendo o custo total calculado e uma mensagem descritiva.
     */
    public CustoResponseDTO calcularCustoDoBanco(int ano, int mes) {

        //1. Descobre o início e fim do mês
        YearMonth anoMes = YearMonth.of(ano, mes);
        LocalDateTime inicio = anoMes.atDay(1).atStartOfDay(); //Dia 01 às 00:00
        LocalDateTime fim = anoMes.atEndOfMonth().atTime(23,59,59); //Último dia às 23:59:59

        //2. Pega as leituras do banco
        List<HotelReading> leiturasDoBanco = repository.findByTimestampBetween(inicio, fim);

        //3. Converte ENTIDADE (Banco) para DTO (Cálculo)
        // Necessário porque o metodo de cálculo usa DTOs
        List<HotelReadingDTO> listaParaCalculo = new ArrayList<>();
        for(HotelReading leitura : leiturasDoBanco) {
            HotelReadingDTO dto = HotelReadingDTO.builder()
                    .demandKw(leitura.getDemandKw())
                    .timestamp(leitura.getTimestamp())
                    .build();

            listaParaCalculo.add(dto);
        }

        //4. Reusa a lógica de cálculo que já existe
        Double valorBruto = calcularCustoTotal(listaParaCalculo);

        //5. Converte para BigDecimal, corta em 2 casas decimais e arredonda para cima
        Double valorArredondado = BigDecimal.valueOf(valorBruto)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        //5. Monta a resposta bonitinha
        String mensagem = String.format("Fatura de %02d/%d calculada com base em %d leituras.", mes,ano, leiturasDoBanco.size());

        return new CustoResponseDTO(valorArredondado, mensagem);
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
