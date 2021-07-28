package br.com.wine.challenge.dto.faixa.atendimento.request;

import lombok.Value;

@Value
public class FaixaAtendimentoPostRequestDTO implements FaixaAtendimentoRequestDTO.CodigoLoja, FaixaAtendimentoRequestDTO.FaixaInicio, FaixaAtendimentoRequestDTO.FaixaFim {

    private String codigoLoja;
    private Long faixaInicio;
    private Long faixaFim;
}
