package br.com.wine.challenge.dto.faixa.atendimento.request;

import lombok.Value;

@Value
public class FaixaAtendimentoPatchRequestDTO  implements FaixaAtendimentoRequestDTO.FaixaInicio, FaixaAtendimentoRequestDTO.FaixaFim {

    private Long faixaInicio;
    private Long faixaFim;

}
